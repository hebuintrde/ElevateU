package com.example.navigation_drawer.Message;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.navigation_drawer.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MessageRepository class that abstracts access to multiple data sources.
 * This class is responsible for handling data operations for Message objects.
 * It provides a clean API to the rest of the application for app data.
 */

public class MessageRepository {
    private MessageDao messageDao;
    private LiveData<List<Message>> allMessages;
    private ExecutorService executorService;

    public MessageRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        messageDao = db.messageDao();
        allMessages = messageDao.getAllMessages();
        executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * Returns a LiveData object containing a list of all messages.
     *
     * @return LiveData object containing all messages.
     */
    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }

    public void insert(final Message message) {
        executorService.execute(() -> messageDao.insert(message));
    }
}
