package com.tech.ayugram.messenger.forwarder;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.MessagesController;
import com.tech.ayugram.messenger.SendMessagesHelper;
import com.tech.ayugram.messenger.UserConfig;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.RequestDelegate;
import com.tech.ayugram.tgnet.TLRPC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

@Keep
public class ForwarderManager {
    private static ForwarderManager instance;
    private final UserConfig userConfig;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    
    private final ConcurrentHashMap<String, ForwardRule> rules = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pattern> compiledPatterns = new ConcurrentHashMap<>();

    private ForwarderManager() {
        userConfig = UserConfig.getInstance();
        loadRules();
    }

    public static synchronized ForwarderManager getInstance() {
        if (instance == null) {
            instance = new ForwarderManager();
        }
        return instance;
    }

    private void loadRules() {
        if (userConfig.forwarderRules == null || userConfig.forwarderRules.isEmpty()) return;
        
        try {
            org.json.JSONArray arr = new org.json.JSONArray(userConfig.forwarderRules);
            for (int i = 0; i < arr.length(); i++) {
                org.json.JSONObject obj = arr.getJSONObject(i);
                ForwardRule rule = new ForwardRule();
                rule.id = obj.getString("id");
                rule.name = obj.getString("name");
                rule.pattern = obj.getString("pattern");
                rule.sourceDialogIds = parseLongArray(obj, "source_dialog_ids");
                rule.targetDialogIds = parseLongArray(obj, "target_dialog_ids");
                rule.enabled = obj.optBoolean("enabled", true);
                rule.forwardAsCopy = obj.optBoolean("forward_as_copy", false);
                rule.addLink = obj.optBoolean("add_link", true);
                
                if (rule.pattern != null && !rule.pattern.isEmpty()) {
                    try {
                        compiledPatterns.put(rule.id, Pattern.compile(rule.pattern, Pattern.CASE_INSENSITIVE));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
                rules.put(rule.id, rule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Long> parseLongArray(org.json.JSONObject obj, String key) {
        List<Long> list = new ArrayList<>();
        try {
            org.json.JSONArray arr = obj.getJSONArray(key);
            for (int i = 0; i < arr.length(); i++) {
                list.add(arr.getLong(i));
            }
        } catch (Exception e) {
            // Ignore
        }
        return list;
    }

    /**
     * Called for each incoming/outgoing message
     */
    public void onMessage(TLRPC.Message message, boolean outgoing) {
        if (!userConfig.forwarderEnabled || message == null) return;
        
        // Check if message matches any rule
        for (ForwardRule rule : rules.values()) {
            if (!rule.enabled) continue;
            
            // Check source dialog filter
            if (!rule.sourceDialogIds.isEmpty() && !rule.sourceDialogIds.contains(message.dialog_id)) {
                continue;
            }
            
            // Check pattern
            if (rule.pattern != null && !rule.pattern.isEmpty() && message.message != null) {
                Pattern pattern = compiledPatterns.get(rule.id);
                if (pattern == null) continue;
                if (!pattern.matcher(message.message).find()) {
                    continue;
                }
            }
            
            // Rule matched - forward to targets
            forwardMessage(message, rule);
        }
    }

    private void forwardMessage(TLRPC.Message message, ForwardRule rule) {
        for (Long targetDialogId : rule.targetDialogIds) {
            if (rule.forwardAsCopy) {
                // Send as copy (re-send content)
                TLRPC.Message forwardMsg = new TLRPC.Message();
                forwardMsg.dialog_id = targetDialogId;
                forwardMsg.message = message.message;
                forwardMsg.media = message.media;
                forwardMsg.random_id = System.currentTimeMillis();
                forwardMsg.out = true;
                forwardMsg.date = (int) (System.currentTimeMillis() / 1000);
                
                if (rule.addLink) {
                    String deepLink = "https://t.me/c/" + Math.abs(message.dialog_id) + "/" + message.id;
                    forwardMsg.message = (forwardMsg.message != null ? forwardMsg.message + "\n\n" : "") + "📨 " + deepLink;
                }
                
                SendMessagesHelper.getInstance().sendMessage(forwardMsg);
            } else {
                // Forward via MTProto messages.forwardMessages
                TLRPC.TL_messages_forwardMessages req = new TLRPC.TL_messages_forwardMessages();
                req.from_peer = MessagesController.getInstance().getInputPeer(message.dialog_id);
                req.id = new ArrayList<>();
                req.id.add(message.id);
                req.to_peer = MessagesController.getInstance().getInputPeer(targetDialogId);
                req.silent = false;
                req.random_id = new ArrayList<>();
                req.random_id.add(System.currentTimeMillis());
                
                ConnectionsManager.getInstance().sendRequest(req, (response, error) -> {
                    // Forwarded
                });
            }
        }
    }

    public void addRule(ForwardRule rule) {
        rule.id = java.util.UUID.randomUUID().toString();
        rules.put(rule.id, rule);
        if (rule.pattern != null && !rule.pattern.isEmpty()) {
            try {
                compiledPatterns.put(rule.id, Pattern.compile(rule.pattern, Pattern.CASE_INSENSITIVE));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        saveRules();
    }

    public void removeRule(String ruleId) {
        rules.remove(ruleId);
        compiledPatterns.remove(ruleId);
        saveRules();
    }

    public void updateRule(ForwardRule rule) {
        rules.put(rule.id, rule);
        if (rule.pattern != null && !rule.pattern.isEmpty()) {
            try {
                compiledPatterns.put(rule.id, Pattern.compile(rule.pattern, Pattern.CASE_INSENSITIVE));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        saveRules();
    }

    public List<ForwardRule> getRules() {
        return new ArrayList<>(rules.values());
    }

    private void saveRules() {
        try {
            org.json.JSONArray arr = new org.json.JSONArray();
            for (ForwardRule rule : rules.values()) {
                org.json.JSONObject obj = new org.json.JSONObject();
                obj.put("id", rule.id);
                obj.put("name", rule.name);
                obj.put("pattern", rule.pattern);
                obj.put("source_dialog_ids", new org.json.JSONArray(rule.sourceDialogIds));
                obj.put("target_dialog_ids", new org.json.JSONArray(rule.targetDialogIds));
                obj.put("enabled", rule.enabled);
                obj.put("forward_as_copy", rule.forwardAsCopy);
                obj.put("add_link", rule.addLink);
                arr.put(obj);
            }
            userConfig.forwarderRules = arr.toString();
            userConfig.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEnabled(boolean enabled) {
        userConfig.forwarderEnabled = enabled;
        userConfig.saveConfig();
    }

    public void shutdown() {
        executor.shutdown();
    }

    static class ForwardRule {
        String id;
        String name;
        String pattern;
        List<Long> sourceDialogIds = new ArrayList<>();
        List<Long> targetDialogIds = new ArrayList<>();
        boolean enabled = true;
        boolean forwardAsCopy = false;
        boolean addLink = true;
    }
}