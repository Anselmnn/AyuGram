package com.tech.ayugram.messenger.search;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;

import com.tech.ayugram.messenger.AyuDatabase;
import com.tech.ayugram.messenger.LocalMessage;
import com.tech.ayugram.messenger.LocalMessageDao;
import com.tech.ayugram.messenger.MessagesController;
import com.tech.ayugram.messenger.OfflineEngine;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.RequestDelegate;
import com.tech.ayugram.tgnet.TLRPC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Keep
public class SmartSearchEngine {
    private static SmartSearchEngine instance;
    private final AyuDatabase database;
    private final LocalMessageDao messageDao;
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    
    private final ConcurrentHashMap<String, SearchSession> activeSessions = new ConcurrentHashMap<>();

    private SmartSearchEngine() {
        database = AyuDatabase.getInstance();
        messageDao = database.localMessageDao();
    }

    public static synchronized SmartSearchEngine getInstance() {
        if (instance == null) {
            instance = new SmartSearchEngine();
        }
        return instance;
    }

    /**
     * §2.1 Умный поиск с логическими операторами
     * Синтаксис: (слово1|слово2|слово3) общее_слово -исключение
     */
    public void search(String query, long dialogId, boolean globalSearch, SearchCallback callback) {
        String sessionId = String.valueOf(System.currentTimeMillis());
        SearchSession session = new SearchSession(sessionId, query, dialogId, globalSearch, callback);
        activeSessions.put(sessionId, session);
        
        // Parse query
        ParsedQuery parsed = parseQuery(query);
        session.parsedQuery = parsed;
        
        if (parsed.subQueries.isEmpty()) {
            callback.onResults(new ArrayList<>());
            activeSessions.remove(sessionId);
            return;
        }
        
        // Execute subqueries in parallel (Scatter-Gather)
        executeSubQueries(session);
    }

    public void cancelSearch(String sessionId) {
        SearchSession session = activeSessions.remove(sessionId);
        if (session != null) {
            session.cancelled = true;
        }
    }

    private ParsedQuery parseQuery(String query) {
        ParsedQuery result = new ParsedQuery();
        
        if (query == null || query.trim().isEmpty()) {
            return result;
        }
        
        String workingQuery = query.trim();
        List<String> excludeWords = new ArrayList<>();
        
        // 1. Парсинг операторов исключения: -исключение
        Pattern excludePattern = Pattern.compile("(?:^|\\s)-([^\\s()|]+)");
        Matcher excludeMatcher = excludePattern.matcher(workingQuery);
        while (excludeMatcher.find()) {
            excludeWords.add(excludeMatcher.group(1).toLowerCase());
        }
        workingQuery = excludePattern.matcher(workingQuery).replaceAll(" ").trim();
        result.excludeWords = excludeWords;
        
        // 2. Раскрытие скобок и декартово произведение
        List<String> subQueries = expandParentheses(workingQuery);
        result.subQueries = subQueries;
        
        return result;
    }

    /**
     * Раскрытие скобок: (a|b) c -> ["a c", "b c"]
     */
    private List<String> expandParentheses(String query) {
        List<String> result = new ArrayList<>();
        int parenStart = query.indexOf('(');
        
        if (parenStart == -1) {
            result.add(query.trim());
            return result;
        }
        
        int parenEnd = findMatchingParenthesis(query, parenStart);
        if (parenEnd == -1) {
            result.add(query.trim());
            return result;
        }
        
        String before = query.substring(0, parenStart).trim();
        String inside = query.substring(parenStart + 1, parenEnd).trim();
        String after = query.substring(parenEnd + 1).trim();
        
        String[] alternatives = inside.split("\\|");
        for (String alt : alternatives) {
            alt = alt.trim();
            String combined = (before + " " + alt + " " + after).trim();
            result.add(combined);
        }
        
        return result;
    }

    private int findMatchingParenthesis(String str, int start) {
        int count = 0;
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == '(') count++;
            else if (str.charAt(i) == ')') {
                count--;
                if (count == 0) return i;
            }
        }
        return -1;
    }

    private void executeSubQueries(SearchSession session) {
        if (session.cancelled) return;
        
        final int[] completed = {0};
        final List<TLRPC.Message> allResults = new ArrayList<>();
        final Set<String> seenIds = new HashSet<>(); // For deduplication
        
        for (String subQuery : session.parsedQuery.subQueries) {
            if (session.cancelled) break;
            
            if (session.globalSearch) {
                // Global search via server
                executeServerSearch(session, subQuery, completed, allResults, seenIds);
            } else {
                // Local chat search
                if (OfflineEngine.getInstance().isOffline()) {
                    executeLocalSearch(session, subQuery, completed, allResults, seenIds);
                } else {
                    executeServerSearch(session, subQuery, completed, allResults, seenIds);
                }
            }
        }
        
        // If no subqueries executed (shouldn't happen), finish
        if (session.parsedQuery.subQueries.isEmpty()) {
            finishSearch(session, allResults);
        }
    }

    private void executeServerSearch(SearchSession session, String subQuery, 
                                     int[] completed, List<TLRPC.Message> allResults, Set<String> seenIds) {
        TLRPC.TL_messages_search req = new TLRPC.TL_messages_search();
        req.peer = MessagesController.getInstance().getInputPeer(session.dialogId);
        req.q = subQuery;
        req.limit = 50;
        
        ConnectionsManager.getInstance().sendRequest(req, (response, error) -> {
            if (!session.cancelled) {
                if (error == null && response instanceof TLRPC.messages_Messages) {
                    TLRPC.messages_Messages msgs = (TLRPC.messages_Messages) response;
                    for (TLRPC.Message msg : msgs.messages) {
                        String key = msg.dialog_id + ":" + msg.id;
                        if (seenIds.add(key)) {
                            // Apply exclude filter
                            if (!matchesExclude(msg, session.parsedQuery.excludeWords)) {
                                allResults.add(msg);
                            }
                        }
                    }
                } else if (error != null && "SEARCH_QUERY_EMPTY".equals(error.text) && session.fromPeer != null) {
                    // §2.2 Fallback: SEARCH_QUERY_EMPTY with from_id -> local search
                    executeLocalSearch(session, subQuery, completed, allResults, seenIds);
                }
            }
            
            synchronized (completed) {
                completed[0]++;
                if (completed[0] >= session.parsedQuery.subQueries.size()) {
                    finishSearch(session, allResults);
                }
            }
        });
    }

    private void executeLocalSearch(SearchSession session, String subQuery,
                                    int[] completed, List<TLRPC.Message> allResults, Set<String> seenIds) {
        executor.execute(() -> {
            if (!session.cancelled) {
                List<LocalMessage> localMsgs = messageDao.searchMessages(
                    UserConfig.getInstance().userId,
                    session.dialogId,
                    "%" + subQuery + "%",
                    100
                );
                
                for (LocalMessage lm : localMsgs) {
                    String key = lm.dialog_id + ":" + lm.message_id;
                    if (seenIds.add(key)) {
                        // Convert back to TLRPC.Message (simplified)
                        TLRPC.Message msg = deserializeMessage(lm);
                        if (msg != null && !matchesExclude(msg, session.parsedQuery.excludeWords)) {
                            synchronized (allResults) {
                                allResults.add(msg);
                            }
                        }
                    }
                }
            }
            
            synchronized (completed) {
                completed[0]++;
                if (completed[0] >= session.parsedQuery.subQueries.size()) {
                    finishSearch(session, allResults);
                }
            }
        });
    }

    private boolean matchesExclude(TLRPC.Message msg, List<String> excludeWords) {
        if (excludeWords.isEmpty() || msg.message == null) return false;
        String lowerText = msg.message.toLowerCase();
        for (String word : excludeWords) {
            if (lowerText.contains(word)) return true;
        }
        return false;
    }

    private void finishSearch(SearchSession session, List<TLRPC.Message> allResults) {
        // Sort by date DESC
        allResults.sort((a, b) -> Integer.compare(b.date, a.date));
        
        mainHandler.post(() -> {
            session.callback.onResults(allResults);
            activeSessions.remove(session.sessionId);
        });
    }

    private TLRPC.Message deserializeMessage(LocalMessage local) {
        try {
            java.io.ByteArrayInputStream bis = new java.io.ByteArrayInputStream(local.raw_data);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bis);
            TLRPC.Message msg = (TLRPC.Message) ois.readObject();
            ois.close();
            return msg;
        } catch (Exception e) {
            // Fallback: create minimal message
            TLRPC.Message msg = new TLRPC.Message();
            msg.id = (int) local.message_id;
            msg.dialog_id = local.dialog_id;
            msg.from_id = local.from_id;
            msg.date = local.date;
            msg.message = local.text;
            msg.out = local.is_out;
            return msg;
        }
    }

    public void shutdown() {
        executor.shutdown();
    }

    static class SearchSession {
        String sessionId;
        String query;
        long dialogId;
        boolean globalSearch;
        SearchCallback callback;
        ParsedQuery parsedQuery;
        TLRPC.InputPeer fromPeer;
        boolean cancelled = false;

        SearchSession(String id, String q, long d, boolean g, SearchCallback cb) {
            sessionId = id;
            query = q;
            dialogId = d;
            globalSearch = g;
            callback = cb;
        }
    }

    static class ParsedQuery {
        List<String> subQueries = new ArrayList<>();
        List<String> excludeWords = new ArrayList<>();
    }

    public interface SearchCallback {
        void onResults(List<TLRPC.Message> results);
    }
}