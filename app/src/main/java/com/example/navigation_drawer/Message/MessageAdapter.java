package com.example.navigation_drawer.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.navigation_drawer.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_TEXT = 1;
    private static final int TYPE_IMAGE = 2;

    private final LayoutInflater mInflater;
    private List<Message> mMessages;
    private Context mContext;

    public MessageAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = mMessages.get(position);
        if (message.getImagePath() != null && !message.getImagePath().isEmpty()) {
            return TYPE_IMAGE;
        } else {
            return TYPE_TEXT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_IMAGE) {
            View itemView = mInflater.inflate(R.layout.recyclerview_image_message_sent_item, parent, false);
            return new ImageMessageViewHolder(itemView);
        } else {
            View itemView = mInflater.inflate(R.layout.recyclerview_text_message_sent_item, parent, false);
            return new TextMessageViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message current = mMessages.get(position);
        if (holder instanceof ImageMessageViewHolder) {
            ImageMessageViewHolder imageHolder = (ImageMessageViewHolder) holder;
            if (current.getImagePath() != null && !current.getImagePath().isEmpty()) {
                Glide.with(imageHolder.imageView.getContext())
                        .load(current.getImagePath())
                        .placeholder(R.drawable.placeholder_image) // Placeholder image
                        .into(imageHolder.imageView);
            }
        } else if (holder instanceof TextMessageViewHolder) {
            TextMessageViewHolder textHolder = (TextMessageViewHolder) holder;
            textHolder.messageItemView.setText(current.getText());
        }
    }

    void setMessages(List<Message> messages) {
        mMessages = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mMessages != null)
            return mMessages.size();
        else return 0;
    }

    class TextMessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageItemView;

        private TextMessageViewHolder(View itemView) {
            super(itemView);
            messageItemView = itemView.findViewById(R.id.textView);
        }
    }

    class ImageMessageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        private ImageMessageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
