package com.tech.ayugram.ui;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.UserConfig;

@Keep
public class AyuGramPreferencesActivity extends AppCompatActivity {
    private UserConfig userConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayugram_preferences);
        
        userConfig = UserConfig.getInstance();
        
        // Ghost Mode
        Switch ghostSwitch = findViewById(R.id.switch_ghost_mode);
        ghostSwitch.setChecked(userConfig.ghostMode);
        ghostSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userConfig.ghostMode = isChecked;
            userConfig.saveConfig();
        });
        
        // Hide Reactions (B1)
        Switch hideReactionsSwitch = findViewById(R.id.switch_hide_reactions);
        hideReactionsSwitch.setChecked(userConfig.hideReactions);
        hideReactionsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userConfig.hideReactions = isChecked;
            userConfig.saveConfig();
        });
        
        // Remember Send Options (B5)
        Switch rememberSendSwitch = findViewById(R.id.switch_remember_send);
        rememberSendSwitch.setChecked(userConfig.rememberSendOptions);
        rememberSendSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userConfig.rememberSendOptions = isChecked;
            userConfig.saveConfig();
        });
        
        // Hide Panel Buttons (B6)
        Switch hidePanelSwitch = findViewById(R.id.switch_hide_panel);
        hidePanelSwitch.setChecked(userConfig.hidePanelButtons);
        hidePanelSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userConfig.hidePanelButtons = isChecked;
            userConfig.saveConfig();
        });
        
        // Local Premium
        Switch premiumSwitch = findViewById(R.id.switch_local_premium);
        premiumSwitch.setChecked(userConfig.localPremium);
        premiumSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userConfig.localPremium = isChecked;
            userConfig.saveConfig();
        });
        
        // Save/Restore
        Switch saveRestoreSwitch = findViewById(R.id.switch_save_restore);
        saveRestoreSwitch.setChecked(userConfig.saveRestoreEnabled);
        saveRestoreSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userConfig.saveRestoreEnabled = isChecked;
            userConfig.saveConfig();
        });
        
        // Regex Filters
        TextView regexFiltersView = findViewById(R.id.text_regex_filters);
        regexFiltersView.setText(userConfig.regexFilters);
        regexFiltersView.setOnClickListener(v -> {
            // Open regex filter editor
        });
        
        // Forwarder
        Switch forwarderSwitch = findViewById(R.id.switch_forwarder);
        forwarderSwitch.setChecked(userConfig.forwarderEnabled);
        forwarderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userConfig.forwarderEnabled = isChecked;
            userConfig.saveConfig();
        });
        
        // Push (R16)
        Switch pushSwitch = findViewById(R.id.switch_push);
        pushSwitch.setChecked(userConfig.pushEnabled);
        pushSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userConfig.pushEnabled = isChecked;
            userConfig.saveConfig();
            // Apply push setting
        });
        
        // GMS Override
        TextView gmsOverrideView = findViewById(R.id.text_gms_override);
        gmsOverrideView.setText(userConfig.gmsOverridePackage);
        gmsOverrideView.setOnClickListener(v -> {
            // Open GMS override editor
        });
    }
}