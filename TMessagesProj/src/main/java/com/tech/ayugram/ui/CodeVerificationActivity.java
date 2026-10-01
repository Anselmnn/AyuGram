package com.tech.ayugram.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.UserConfig;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.TLRPC;

@Keep
public class CodeVerificationActivity extends AppCompatActivity {
    private EditText codeEditText;
    private Button verifyButton;
    private ProgressBar progressBar;
    private TextView errorTextView;
    
    private String phone;
    private String phoneHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verification);
        
        phone = getIntent().getStringExtra("phone");
        phoneHash = getIntent().getStringExtra("phone_hash");
        
        codeEditText = findViewById(R.id.code_edit);
        verifyButton = findViewById(R.id.verify_button);
        progressBar = findViewById(R.id.progress_bar);
        errorTextView = findViewById(R.id.error_text);
        
        verifyButton.setOnClickListener(v -> verifyCode());
    }

    private void verifyCode() {
        String code = codeEditText.getText().toString().trim();
        if (code.isEmpty()) {
            errorTextView.setText("Enter code");
            return;
        }
        
        progressBar.setVisibility(View.VISIBLE);
        verifyButton.setEnabled(false);
        
        TLRPC.TL_auth_signIn req = new TLRPC.TL_auth_signIn();
        req.phone_number = phone;
        req.phone_code_hash = phoneHash;
        req.phone_code = code;
        
        ConnectionsManager.getInstance().sendRequest(req, (response, error) -> {
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                verifyButton.setEnabled(true);
                
                if (error != null) {
                    if (error.text.contains("PHONE_CODE_INVALID")) {
                        errorTextView.setText("Invalid code");
                    } else {
                        errorTextView.setText("Error: " + error.text);
                    }
                } else {
                    // Success - save user config
                    TLRPC.User user = (TLRPC.User) response;
                    UserConfig config = UserConfig.getInstance();
                    config.userId = user.id;
                    config.firstName = user.first_name;
                    config.lastName = user.last_name;
                    config.userName = user.username;
                    config.phoneNumber = phone;
                    config.phoneHash = phoneHash;
                    config.registered = true;
                    config.saveConfig();
                    
                    startActivity(new Intent(this, DialogsActivity.class));
                    finish();
                }
            });
        });
    }
}