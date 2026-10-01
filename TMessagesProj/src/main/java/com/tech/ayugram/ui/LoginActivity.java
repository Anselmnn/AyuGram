package com.tech.ayugram.ui;

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
public class LoginActivity extends AppCompatActivity {
    private EditText phoneEditText;
    private Button nextButton;
    private ProgressBar progressBar;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        phoneEditText = findViewById(R.id.phone_edit);
        nextButton = findViewById(R.id.next_button);
        progressBar = findViewById(R.id.progress_bar);
        errorTextView = findViewById(R.id.error_text);
        
        nextButton.setOnClickListener(v -> sendCode());
    }

    private void sendCode() {
        String phone = phoneEditText.getText().toString().trim();
        if (phone.isEmpty()) {
            errorTextView.setText("Enter phone number");
            return;
        }
        
        progressBar.setVisibility(View.VISIBLE);
        nextButton.setEnabled(false);
        
        // Send auth code request
        TLRPC.TL_auth_sendCode req = new TLRPC.TL_auth_sendCode();
        req.phone_number = phone;
        req.api_id = ApplicationLoader.APP_API_ID;
        req.api_hash = ApplicationLoader.APP_API_HASH;
        req.settings = new TLRPC.TL_codeSettings();
        
        ConnectionsManager.getInstance().sendRequest(req, (response, error) -> {
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                nextButton.setEnabled(true);
                
                if (error != null) {
                    errorTextView.setText("Error: " + error.text);
                } else {
                    // Go to code verification
                    startActivity(new Intent(this, CodeVerificationActivity.class)
                        .putExtra("phone", phone)
                        .putExtra("phone_hash", ((TLRPC.TL_auth_sentCode) response).phone_code_hash));
                }
            });
        });
    }
}