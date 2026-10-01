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
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<TLRPC.Message> messages = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TLRPC.Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<TLRPC.Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        TextView timeTextView;

        ViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.message_text);
            timeTextView = itemView.findViewById(R.id.message_time);
        }

        void bind(TLRPC.Message message) {
            messageTextView.setText(message.message);
            timeTextView.setText(String.valueOf(message.date));
        }
    }
}