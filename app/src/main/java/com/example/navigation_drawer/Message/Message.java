package com.example.navigation_drawer.Message;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * Message is an entity class that represents a table in the Room database.
 * Each instance of this class represents a row in the 'messages' table.
 */
@Entity(tableName = "messages")
public class Message {
    /**
     * Primary key for the Message entity, auto-generated.
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private String imagePath;


    public Message(String text, String imagePath) {
        this.text = text;
        this.imagePath = imagePath;

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
