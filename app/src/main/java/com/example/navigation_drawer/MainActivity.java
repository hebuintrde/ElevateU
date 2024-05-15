package com.example.navigation_drawer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer=findViewById(R.id.cekmece_arkapalan);


    }
    //baskasayfa da da kullnamak istiyoeum (public)
    public void MenuyeTiklama (View view)
    {

        openthedrawer(drawer);
    }

    private void openthedrawer(DrawerLayout drawer) {
        drawer.openDrawer(GravityCompat.START);
    }

    public void AccountClick (View view)
    {

        closethedrawer(drawer);
    }

    private void closethedrawer(DrawerLayout drawer)
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void HomePageClick (View view)
    {

        recreate();
    }


    public void ChatClick (View view)
    {
        //Accesofchat
        Toast.makeText(this,"Chatpanel",Toast.LENGTH_SHORT).show();
    }

    public void AboutUsClick (View view)
    {
        //Accesofüberuns
        Toast.makeText(this,"ÜberunsPanel",Toast.LENGTH_SHORT).show();


    }

    public void MapsClick (View view)
    {
        //Accesofmaps
        Toast.makeText(this,"Mapspanel",Toast.LENGTH_SHORT).show();


    }

    public void ExitClick (View view)
    {
        //exit
        AlertDialog.Builder warningwindow = new AlertDialog.Builder(MainActivity.this);

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

    protected void onPause(){

        closethedrawer(drawer);

        super.onPause();
    }

}