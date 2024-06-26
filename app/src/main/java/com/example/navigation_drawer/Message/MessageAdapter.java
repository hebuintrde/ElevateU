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

/**
 * RecyclerView Adapter class for displaying a list of Message objects.
 * It supports two types of messages: text and image messages.
 */

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


    /**
     * Returns the view type of the item at the given position.
     *
     * @param position The position of the item.
     * @return The view type of the item.
     */
    @Override
    public int getItemViewType(int position) {
        Message message = mMessages.get(position);
        if (message.getImagePath() != null && !message.getImagePath().isEmpty()) {
            return TYPE_IMAGE;
        } else {
            return TYPE_TEXT;
        }
    }


    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new view will be added after it is bound to an adapter position.
     * @param viewType The view type of the new view.
     * @return A new RecyclerView.ViewHolder that holds a View of the given view type.
     */
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


    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
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
