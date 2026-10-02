package com.tech.ayugram.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.MessagesController;
import com.tech.ayugram.messenger.SendMessagesHelper;
import com.tech.ayugram.tgnet.TLRPC;

@Keep
public class ChatActivity extends AppCompatActivity {
    private long dialogId;
    private RecyclerView messagesRecyclerView;
    private EditText messageEditText;
    private ImageButton sendButton;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        
        dialogId = getIntent().getLongExtra("dialog_id", 0);
        
        messagesRecyclerView = findViewById(R.id.messages_recycler);
        messageEditText = findViewById(R.id.message_edit);
        sendButton = findViewById(R.id.send_button);
        
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter();
        messagesRecyclerView.setAdapter(chatAdapter);
        
        sendButton.setOnClickListener(v -> sendMessage());
        
        loadMessages();
    }

    private void loadMessages() {
        MessagesController.getInstance().getMessages(dialogId, 0, 50, (response, error) -> {
            if (error == null) {
                runOnUiThread(() -> chatAdapter.notifyDataSetChanged());
            }
        });
    }

    private void sendMessage() {
        String text = messageEditText.getText().toString().trim();
        if (text.isEmpty()) return;
        
        TLRPC.Message message = new TLRPC.Message();
        message.dialog_id = dialogId;
        message.message = text;
        message.random_id = System.currentTimeMillis();
        message.out = true;
        message.date = (int) (System.currentTimeMillis() / 1000);
        
        SendMessagesHelper.getInstance().sendMessage(message);
        messageEditText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }
}