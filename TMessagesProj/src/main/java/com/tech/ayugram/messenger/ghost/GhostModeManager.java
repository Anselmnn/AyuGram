package com.tech.ayugram.messenger.ghost;

import android.content.Context;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.NotificationsController;
import com.tech.ayugram.messenger.UserConfig;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.TLRPC;

@Keep
public class GhostModeManager {
    private static GhostModeManager instance;
    private final UserConfig userConfig;

    private GhostModeManager() {
        userConfig = UserConfig.getInstance();
    }

    public static synchronized GhostModeManager getInstance() {
        if (instance == null) {
            instance = new GhostModeManager();
        }
        return instance;
    }

    /**
     * R15 B1: Hide reactions - скрыть счетчики реакций и анимации
     */
    public boolean isHideReactionsEnabled() {
        return userConfig.ghostMode && userConfig.hideReactions;
    }

    /**
     * R15 B5: Remember send options - запомнить последние настройки отправки
     * (silent, schedule, noforwards)
     */
    public boolean isRememberSendOptionsEnabled() {
        return userConfig.ghostMode && userConfig.rememberSendOptions;
    }

    /**
     * R15 B6: Hide panel buttons - скрыть кнопки прикрепления/медиа/эмодзи в панели ввода
     */
    public boolean isHidePanelButtonsEnabled() {
        return userConfig.ghostMode && userConfig.hidePanelButtons;
    }

    /**
     * Main ghost mode toggle - скрывает online, typing, read receipts
     */
    public boolean isGhostModeEnabled() {
        return userConfig.ghostMode;
    }

    public void setGhostMode(boolean enabled) {
        userConfig.ghostMode = enabled;
        userConfig.saveConfig();
        applyGhostModeSettings();
    }

    public void setHideReactions(boolean enabled) {
        userConfig.hideReactions = enabled;
        userConfig.saveConfig();
    }

    public void setRememberSendOptions(boolean enabled) {
        userConfig.rememberSendOptions = enabled;
        userConfig.saveConfig();
    }

    public void setHidePanelButtons(boolean enabled) {
        userConfig.hidePanelButtons = enabled;
        userConfig.saveConfig();
    }

    private void applyGhostModeSettings() {
        if (userConfig.ghostMode) {
            // Set privacy settings to maximum
            setPrivacyLastSeen(TLRPC.TL_privacyValueDisallowAll.CONSTRUCTOR);
            setPrivacyProfilePhoto(TLRPC.TL_privacyValueDisallowAll.CONSTRUCTOR);
            setPrivacyCalls(TLRPC.TL_privacyValueDisallowAll.CONSTRUCTOR);
            setPrivacyForwards(TLRPC.TL_privacyValueDisallowAll.CONSTRUCTOR);
            setPrivacyGroups(TLRPC.TL_privacyValueDisallowAll.CONSTRUCTOR);
            
            // Disable read receipts for outgoing
            SharedConfig.noPreview = true;
        }
    }

    // Privacy settings helpers
    private void setPrivacyLastSeen(int value) {
        TLRPC.TL_account_setPrivacy req = new TLRPC.TL_account_setPrivacy();
        req.key = new TLRPC.TL_inputPrivacyKeyStatusTimestamp();
        req.rules = new java.util.ArrayList<>();
        TLRPC.TL_privacyValueDisallowAll rule = new TLRPC.TL_privacyValueDisallowAll();
        req.rules.add(rule);
        ConnectionsManager.getInstance().sendRequest(req, null);
    }

    private void setPrivacyProfilePhoto(int value) {
        TLRPC.TL_account_setPrivacy req = new TLRPC.TL_account_setPrivacy();
        req.key = new TLRPC.TL_inputPrivacyKeyProfilePhoto();
        req.rules = new java.util.ArrayList<>();
        TLRPC.TL_privacyValueDisallowAll rule = new TLRPC.TL_privacyValueDisallowAll();
        req.rules.add(rule);
        ConnectionsManager.getInstance().sendRequest(req, null);
    }

    private void setPrivacyCalls(int value) {
        TLRPC.TL_account_setPrivacy req = new TLRPC.TL_account_setPrivacy();
        req.key = new TLRPC.TL_inputPrivacyKeyPhoneCall();
        req.rules = new java.util.ArrayList<>();
        TLRPC.TL_privacyValueDisallowAll rule = new TLRPC.TL_privacyValueDisallowAll();
        req.rules.add(rule);
        ConnectionsManager.getInstance().sendRequest(req, null);
    }

    private void setPrivacyForwards(int value) {
        TLRPC.TL_account_setPrivacy req = new TLRPC.TL_account_setPrivacy();
        req.key = new TLRPC.TL_inputPrivacyKeyForwards();
        req.rules = new java.util.ArrayList<>();
        TLRPC.TL_privacyValueDisallowAll rule = new TLRPC.TL_privacyValueDisallowAll();
        req.rules.add(rule);
        ConnectionsManager.getInstance().sendRequest(req, null);
    }

    private void setPrivacyGroups(int value) {
        TLRPC.TL_account_setPrivacy req = new TLRPC.TL_account_setPrivacy();
        req.key = new TLRPC.TL_inputPrivacyKeyChatInvite();
        req.rules = new java.util.ArrayList<>();
        TLRPC.TL_privacyValueDisallowAll rule = new TLRPC.TL_privacyValueDisallowAll();
        req.rules.add(rule);
        ConnectionsManager.getInstance().sendRequest(req, null);
    }

    /**
     * Override typing indicator - не отправлять typing update
     */
    public boolean shouldSendTyping() {
        return !userConfig.ghostMode;
    }

    /**
     * Override read receipts - не отправлять readReceived
     */
    public boolean shouldSendReadReceipt() {
        return !userConfig.ghostMode;
    }

    /**
     * Override online status - не отправлять Online update
     */
    public boolean shouldUpdateOnlineStatus() {
        return !userConfig.ghostMode;
    }
}