package com.example.navigation_drawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {
    private Button openSurgicalProceduresActivity;
    private Button openNonSurgicalProceduresActivity;

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer=findViewById(R.id.drawer_background);

        openSurgicalProceduresActivity = findViewById(R.id.surgical_button);
        openSurgicalProceduresActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent surgical_intent = new Intent(MainActivity.this,SurgicalProceduresDescriptions.class);
                startActivity(surgical_intent);
            }
        });

        openNonSurgicalProceduresActivity = findViewById(R.id.non_surgical_button);
        openNonSurgicalProceduresActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent non_surgical_intent = new Intent(MainActivity.this, NonSurgicalProceduresDescriptions.class);
                startActivity(non_surgical_intent);
            }
        });



    }
    //i want to use it in another  page (public)
    //What should it do, after i click the menu = open the drawer
    public void MenuClick (View view)
    {
        //Connection with Method open the drawer
        openthedrawer(drawer);
    }

    //Method open drawer
    public static void openthedrawer(DrawerLayout drawer)
    {
        // Drawer to be opened from right
        drawer.openDrawer(GravityCompat.START);
    }

    public void LogoClick(View view)
    {

        closethedrawer(drawer);
    }

    public static void closethedrawer(DrawerLayout drawer)
    {
        //if drawer is open
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void HomePageClick (View view)
    {

        recreate();
    }

    public void AccountClick (View view)
    {
        //AccesofAccount
        Intent account = new Intent(MainActivity.this,AccountActivity.class);
        account.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(account);
        finish();

    }

    public void ChatClick (View view)
    {
        //Accesofchat
        Intent chat = new Intent(MainActivity.this,ChatActivity.class);
        chat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(chat);
        finish();
    }

    public void AboutUsClick (View view)
    {
        //Accesofchat
        Intent aboutUs = new Intent(MainActivity.this,AboutUsActivity.class);
        //finish Activity, dont come back
        aboutUs.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(aboutUs);
        finish();


    }

    public void MapsClick (View view)
    {
        //Accesofmaps
        Intent maps = new Intent(MainActivity.this,MapsActivity.class);
        //finish Activity, dont come back
        maps.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(maps);
        finish();


    }

    public void ExitClick (View view)
    {
        //exit
        AlertDialog.Builder warningwindow = new AlertDialog.Builder(MainActivity.this);

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

        closethedrawer(drawer);

        super.onPause();
    }

}