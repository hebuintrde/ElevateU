package com.example.navigation_drawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class ChatActivity extends AppCompatActivity {

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        drawer=findViewById(R.id.drawer_background);

    }

    public void MenuClick (View view)
    {
        //Open the drawer that stand in main Activity
        MainActivity.openthedrawer(drawer);
    }

    public void LogoClick(View view)
    {

        MainActivity.closethedrawer(drawer);
    }

    public void HomePageClick (View view)
    {
        //Switching between pages
        Intent homepage = new Intent(ChatActivity.this,MainActivity.class);
        homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(homepage);
        finish();

    }

    public void AccountClick (View view)
    {
        Intent account = new Intent(ChatActivity.this,AccountActivity.class);
        account.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(account);
        finish();

    }

    public void ChatClick (View view)
    {

        recreate();
    }

    public void AboutUsClick (View view)
    {
        Intent aboutUs = new Intent(ChatActivity.this,AboutUsActivity.class);
        //finish Activity, dont come back
        aboutUs.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(aboutUs);
        finish();


    }

    public void MapsClick (View view)
    {
        //Accesofmaps
        Intent maps = new Intent(ChatActivity.this,MapsActivity.class);
        //finish Activity, dont come back
        maps.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(maps);
        finish();;


    }

    public void ExitClick (View view)
    {
        //exit
        AlertDialog.Builder warningwindow = new AlertDialog.Builder(ChatActivity.this);

        warningwindow.setTitle("Exit");
        warningwindow.setMessage("Are you sure you want to sign out?");

        //if yes
        warningwindow.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finishAffinity();
                System.exit(0);
            }
        });
        //if no
        warningwindow.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        //show the warningwindow
        warningwindow.show();

    }

    //If the program is stopped, close the drawer
    protected void onPause(){

        MainActivity.closethedrawer(drawer);

        super.onPause();
    }

}