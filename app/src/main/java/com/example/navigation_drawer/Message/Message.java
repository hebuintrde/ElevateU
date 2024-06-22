package com.example.navigation_drawer.Message;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigation_drawer.ChatActivity;
import com.example.navigation_drawer.R;

public class Message extends AppCompatActivity {


    private ImageButton sendButton, sendpotoButton;

    private EditText messageEditText;
    private ScrollView messageScrollView;

    private TextView showmessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message);



        sendButton = findViewById(R.id.send_button);
        messageEditText = findViewById(R.id.message_input);
        messageScrollView = findViewById(R.id.my_scroll_view);
        showmessages = findViewById(R.id.message_title);
        sendpotoButton = findViewById(R.id.camera_button);



    }

    @Override
    public void onBackPressed() {
        // Extra operations

Intent intent = new Intent(Message.this, ChatActivity.class);
startActivity(intent);

        // Call the super method to handle the back navigation
        super.onBackPressed();
    }
}