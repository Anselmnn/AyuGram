package com.tech.ayugram.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.MessagesController;
import com.tech.ayugram.messenger.UserConfig;

@Keep
public class DialogsActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private RecyclerView dialogsRecyclerView;
    private DialogsAdapter dialogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
        
        drawerLayout = findViewById(R.id.drawer_layout);
        dialogsRecyclerView = findViewById(R.id.dialogs_recycler);
        dialogsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        dialogsAdapter = new DialogsAdapter();
        dialogsRecyclerView.setAdapter(dialogsAdapter);
        
        // Load dialogs
        loadDialogs();
    }

    private void loadDialogs() {
        MessagesController.getInstance().getDialogs(0, 100, false, (response, error) -> {
            if (error == null && response instanceof com.tech.ayugram.tgnet.TLRPC.messages_Dialogs) {
                // Update UI on main thread
                runOnUiThread(() -> {
                    dialogsAdapter.notifyDataSetChanged();
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dialogs_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_ayugram_settings) {
            startActivity(new Intent(this, AyuGramPreferencesActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}