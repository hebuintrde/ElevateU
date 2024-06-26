package com.example.navigation_drawer.Message;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * MessageViewModel class that is responsible for preparing and managing the data for an Activity or a Fragment.
 * It handles the communication of the Activity / Fragment with the data sources (MessageRepository).
 */

public class MessageViewModel extends AndroidViewModel {
    private MessageRepository repository;
    private LiveData<List<Message>> allMessages;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        repository = new MessageRepository(application);
        allMessages = repository.getAllMessages();
    }
    /**
     * Returns a LiveData object containing a list of all messages.
     *
     * @return LiveData object containing all messages.
     */
    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }


    /**
     * Inserts a message into the database via the repository.
     *
     * @param message The message to be inserted.
     */
    public void insert(Message message) {
        repository.insert(message);
    }
}
