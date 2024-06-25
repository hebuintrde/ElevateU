package com.example.navigation_drawer.Message;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation_drawer.AboutUsActivity;
import com.example.navigation_drawer.AccountActivity;
import com.example.navigation_drawer.ChatActivity;
import com.example.navigation_drawer.MainActivity;
import com.example.navigation_drawer.Maps.MapsActivity;
import com.example.navigation_drawer.R;

public class MessageActivity extends AppCompatActivity {

    private MessageViewModel messageViewModel;
    private EditText messageInput;
    private ImageButton sendMessageButton, sendPhotoButton;
    DrawerLayout drawer;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 102;

    private String[] cameraPermissions;

    private Uri resimUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        RecyclerView recyclerView = findViewById(R.id.message_recycler_view);
        final MessageAdapter adapter = new MessageAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        messageViewModel.getAllMessages().observe(this, adapter::setMessages);

        messageInput = findViewById(R.id.message_input);
        sendPhotoButton = findViewById(R.id.camera_button);
        sendMessageButton = findViewById(R.id.send_button);

        drawer = findViewById(R.id.drawer_background);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        sendPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosephoto();
            }
        });

        sendMessageButton.setOnClickListener(v -> {
            String text = messageInput.getText().toString().trim();
            if (!text.isEmpty()) {
                Message message = new Message(text, null);
                messageViewModel.insert(message);
                messageInput.setText("");
            }
        });
    }

    private void chosephoto() {
        if (!camerapremissioncontrol()) {
            requestCameraPermission();
        } else {
            opencamera();
        }
    }

    private void opencamera() {
        ContentValues degerler = new ContentValues();
        degerler.put(MediaStore.Images.Media.TITLE, "Image_Title");
        degerler.put(MediaStore.Images.Media.DESCRIPTION, "Image Description");
        resimUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, degerler);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, resimUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    private boolean camerapremissioncontrol() {
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        return result1 && result;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                Log.d("CameraImagePath", "Image URI: " + resimUri);
                Message message = new Message("", resimUri.toString());
                messageViewModel.insert(message);
            }
        }
    }

    // Navigation drawer methods
    public void MenuClick(View view) {
        MainActivity.openthedrawer(drawer);
    }

    public void LogoClick(View view) {
        MainActivity.closethedrawer(drawer);
    }

    public void HomePageClick(View view) {
        Intent homepage = new Intent(MessageActivity.this, MainActivity.class);
        startActivity(homepage);
    }

    public void AccountClick(View view) {
        Intent account = new Intent(MessageActivity.this, AccountActivity.class);
        startActivity(account);
    }

    public void ChatClick(View view) {
        Intent chat = new Intent(MessageActivity.this, ChatActivity.class);
        startActivity(chat);
    }

    public void AboutUsClick(View view) {
        Intent aboutUs = new Intent(MessageActivity.this, AboutUsActivity.class);
        startActivity(aboutUs);
    }

    public void MapsClick(View view) {
        Intent maps = new Intent(MessageActivity.this, MapsActivity.class);
        startActivity(maps);
    }

    public void ExitClick(View view) {
        AlertDialog.Builder warningwindow = new AlertDialog.Builder(MessageActivity.this);
        warningwindow.setTitle("Exit");
        warningwindow.setMessage("Are you sure you want to sign out?");
        warningwindow.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                System.exit(0);
            }
        });
        warningwindow.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        warningwindow.show();
    }

    @Override
    protected void onPause() {
        MainActivity.closethedrawer(drawer);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent chatIntent = new Intent(MessageActivity.this, ChatActivity.class);
        startActivity(chatIntent);
        finish(); // Optional: Use this if you want to finish the current activity
    }
}
