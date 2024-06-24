package com.example.navigation_drawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.navigation_drawer.Maps.MapsActivity;

public class AboutUsActivity extends AppCompatActivity {

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        drawer = findViewById(R.id.drawer_background);
    }

    public void MenuClick(View view) {
        MainActivity.openthedrawer(drawer);
    }

    public void LogoClick(View view) {
        MainActivity.closethedrawer(drawer);
    }

    public void HomePageClick(View view) {
        Intent homepage = new Intent(AboutUsActivity.this, MainActivity.class);
        startActivity(homepage);
    }

    public void AccountClick(View view) {
        Intent account = new Intent(AboutUsActivity.this, AccountActivity.class);
        startActivity(account);
    }

    public void ChatClick(View view) {
        Intent chat = new Intent(AboutUsActivity.this, ChatActivity.class);
        startActivity(chat);
    }

    public void AboutUsClick(View view) {
        recreate();
    }

    public void MapsClick(View view) {
        Intent maps = new Intent(AboutUsActivity.this, MapsActivity.class);
        startActivity(maps);
    }

    public void ExitClick(View view) {
        AlertDialog.Builder warningwindow = new AlertDialog.Builder(AboutUsActivity.this);
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
}
