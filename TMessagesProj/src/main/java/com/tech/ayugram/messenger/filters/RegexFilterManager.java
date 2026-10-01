package com.tech.ayugram.messenger.filters;

import androidx.annotation.Keep;

import com.tech.ayugram.messenger.UserConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Keep
public class RegexFilterManager {
    private static RegexFilterManager instance;
    private final UserConfig userConfig;
    
    private final ConcurrentHashMap<String, FilterRule> rules = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pattern> compiledPatterns = new ConcurrentHashMap<>();

    private RegexFilterManager() {
        userConfig = UserConfig.getInstance();
        loadRules();
    }

    public static synchronized RegexFilterManager getInstance() {
        if (instance == null) {
            instance = new RegexFilterManager();
        }
        return instance;
    }

    private void loadRules() {
        if (userConfig.regexFilters == null || userConfig.regexFilters.isEmpty()) return;
        
        try {
            JSONArray arr = new JSONArray(userConfig.regexFilters);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                FilterRule rule = new FilterRule();
                rule.id = obj.getString("id");
                rule.name = obj.getString("name");
                rule.pattern = obj.getString("pattern");
                rule.action = obj.getString("action"); // "highlight", "notify", "hide", "forward"
                rule.color = obj.optString("color", "#FF0000");
                rule.enabled = obj.optBoolean("enabled", true);
                rule.caseSensitive = obj.optBoolean("case_sensitive", false);
                rule.wholeWords = obj.optBoolean("whole_words", false);
                
                if (rule.pattern != null && !rule.pattern.isEmpty()) {
                    int flags = Pattern.MULTILINE;
                    if (!rule.caseSensitive) flags |= Pattern.CASE_INSENSITIVE;
                    if (rule.wholeWords) {
                        // Wrap with word boundaries
                        rule.pattern = "\\b" + rule.pattern + "\\b";
                    }
                    try {
                        compiledPatterns.put(rule.id, Pattern.compile(rule.pattern, flags));
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

    /**
     * Check if text matches any filter rule
     * Returns the first matching rule or null
     */
    public FilterRule match(String text) {
        if (text == null) return null;
        
        for (FilterRule rule : rules.values()) {
            if (!rule.enabled) continue;
            
            Pattern pattern = compiledPatterns.get(rule.id);
            if (pattern == null) continue;
            
            if (pattern.matcher(text).find()) {
                return rule;
            }
        }
        return null;
    }

    /**
     * Get all matches in text
     */
    public List<FilterRule> matchAll(String text) {
        List<FilterRule> matches = new ArrayList<>();
        if (text == null) return matches;
        
        for (FilterRule rule : rules.values()) {
            if (!rule.enabled) continue;
            
            Pattern pattern = compiledPatterns.get(rule.id);
            if (pattern == null) continue;
            
            if (pattern.matcher(text).find()) {
                matches.add(rule);
            }
        }
        return matches;
    }

    public void addRule(FilterRule rule) {
        rule.id = java.util.UUID.randomUUID().toString();
        rules.put(rule.id, rule);
        compilePattern(rule);
        saveRules();
    }

    public void removeRule(String ruleId) {
        rules.remove(ruleId);
        compiledPatterns.remove(ruleId);
        saveRules();
    }

    public void updateRule(FilterRule rule) {
        rules.put(rule.id, rule);
        compilePattern(rule);
        saveRules();
    }

    public void toggleRule(String ruleId, boolean enabled) {
        FilterRule rule = rules.get(ruleId);
        if (rule != null) {
            rule.enabled = enabled;
            saveRules();
        }
    }

    public List<FilterRule> getRules() {
        return new ArrayList<>(rules.values());
    }

    private void compilePattern(FilterRule rule) {
        if (rule.pattern != null && !rule.pattern.isEmpty()) {
            int flags = Pattern.MULTILINE;
            if (!rule.caseSensitive) flags |= Pattern.CASE_INSENSITIVE;
            String patternStr = rule.pattern;
            if (rule.wholeWords) {
                patternStr = "\\b" + patternStr + "\\b";
            }
            try {
                compiledPatterns.put(rule.id, Pattern.compile(patternStr, flags));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveRules() {
        try {
            JSONArray arr = new JSONArray();
            for (FilterRule rule : rules.values()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rule.id);
                obj.put("name", rule.name);
                obj.put("pattern", rule.pattern);
                obj.put("action", rule.action);
                obj.put("color", rule.color);
                obj.put("enabled", rule.enabled);
                obj.put("case_sensitive", rule.caseSensitive);
                obj.put("whole_words", rule.wholeWords);
                arr.put(obj);
            }
            userConfig.regexFilters = arr.toString();
            userConfig.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class FilterRule {
        String id;
        String name;
        String pattern;
        String action; // "highlight", "notify", "hide", "forward"
        String color;
        boolean enabled = true;
        boolean caseSensitive = false;
        boolean wholeWords = false;
    }
}