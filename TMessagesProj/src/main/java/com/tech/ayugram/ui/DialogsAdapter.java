package com.tech.ayugram.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.ayugram.tgnet.TLRPC;

import java.util.ArrayList;
import java.util.List;

@Keep
public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.ViewHolder> {
    private List<TLRPC.Dialog> dialogs = new ArrayList<>();
    private List<TLRPC.User> users = new ArrayList<>();
    private List<TLRPC.Chat> chats = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_dialog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TLRPC.Dialog dialog = dialogs.get(position);
        holder.bind(dialog);
    }

    @Override
    public int getItemCount() {
        return dialogs.size();
    }

    public void setDialogs(List<TLRPC.Dialog> dialogs, List<TLRPC.User> users, List<TLRPC.Chat> chats) {
        this.dialogs = dialogs;
        this.users = users;
        this.chats = chats;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView messageTextView;
        TextView timeTextView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.dialog_name);
            messageTextView = itemView.findViewById(R.id.dialog_message);
            timeTextView = itemView.findViewById(R.id.dialog_time);
        }

        void bind(TLRPC.Dialog dialog) {
            // Find user or chat
            String name = "Unknown";
            for (TLRPC.User user : users) {
                if (user.id == dialog.peer_id) {
                    name = user.first_name + " " + user.last_name;
                    break;
                }
            }
            for (TLRPC.Chat chat : chats) {
                if (chat.id == -dialog.peer_id) {
                    name = chat.title;
                    break;
                }
            }
            
            nameTextView.setText(name);
            messageTextView.setText("Last message");
            timeTextView.setText("12:00");
        }
    }
}