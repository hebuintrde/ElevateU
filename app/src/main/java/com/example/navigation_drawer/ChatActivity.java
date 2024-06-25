package com.example.navigation_drawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.gridlayout.widget.GridLayout;


import com.example.navigation_drawer.Doctor.Doctor;
import com.example.navigation_drawer.Doctor.DoctorDetailActivity;
import com.example.navigation_drawer.Maps.MapsActivity;
import com.example.navigation_drawer.Message.MessageActivity;

import java.util.List;
import java.util.concurrent.Executors;

public class ChatActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private GridLayout gridLayout;

    private Button Chatbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        drawer = findViewById(R.id.drawer_background);
        gridLayout = findViewById(R.id.gridLayout);


        Chatbutton = findViewById(R.id.chat_button);
        Chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat = new Intent(ChatActivity.this, MessageActivity.class); // Replace ChatTargetActivity with the correct Activity
                chat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(chat);
                finish();
            }
        });



        loadDoctors();
    }

    private void loadDoctors() {
        MyDatabase db = MyDatabase.getDatabase(this);

        Executors.newSingleThreadExecutor().execute(() -> {
            List<Doctor> doctors = db.doctorDao().getAllDoctors();
            runOnUiThread(() -> {
                for (Doctor doctor : doctors) {
                    addDoctorToGrid(doctor);
                }
            });
        });
    }

    private void addDoctorToGrid(Doctor doctor) {
        View doctorView = LayoutInflater.from(this).inflate(R.layout.item_doctor, gridLayout, false);

        TextView name = doctorView.findViewById(R.id.textView_name);
        TextView specialization = doctorView.findViewById(R.id.textView_specialization);

        name.setText(doctor.getName());
        specialization.setText(doctor.getSpecialization());

        doctorView.setOnClickListener(v -> {
            Intent intent = new Intent(ChatActivity.this, DoctorDetailActivity.class);
            intent.putExtra("doctor_id", doctor.getId());
            startActivity(intent);
        });

        gridLayout.addView(doctorView);
    }


    public void MenuClick(View view) {
        // Open the drawer that stand in main Activity
        MainActivity.openthedrawer(drawer);
    }

    public void LogoClick(View view) {
        MainActivity.closethedrawer(drawer);
    }

    public void HomePageClick(View view) {
        // Switching between pages
        Intent homepage = new Intent(ChatActivity.this, MainActivity.class);
        homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homepage);
        finish();
    }

    public void AccountClick(View view) {
        Intent account = new Intent(ChatActivity.this, AccountActivity.class);
        account.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(account);
        finish();
    }

    public void ChatClick(View view) {
        recreate();
    }

    public void AboutUsClick(View view) {
        Intent aboutUs = new Intent(ChatActivity.this, AboutUsActivity.class);
        // Finish Activity, don't come back
        aboutUs.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(aboutUs);
        finish();
    }

    public void MapsClick(View view) {
        // Access maps
        Intent maps = new Intent(ChatActivity.this, MapsActivity.class);
        // Finish Activity, don't come back
        maps.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(maps);
        finish();
    }

    public void ExitClick(View view) {
        // Exit
        AlertDialog.Builder warningwindow = new AlertDialog.Builder(ChatActivity.this);

        warningwindow.setTitle("Exit");
        warningwindow.setMessage("Are you sure you want to sign out?");

        // If yes
        warningwindow.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                System.exit(0);
            }
        });

        // If no
        warningwindow.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        // Show the warning window
        warningwindow.show();
    }

    @Override
    protected void onPause() {
        MainActivity.closethedrawer(drawer);
        super.onPause();
    }
}
