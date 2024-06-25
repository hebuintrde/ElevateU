package com.example.navigation_drawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.navigation_drawer.Doctor.Doctor;
import com.example.navigation_drawer.Maps.MapsActivity;
import com.example.navigation_drawer.NonSurgicalProcedures.NonSurgicalProceduresDescriptions;
import com.example.navigation_drawer.ProfileAndNavigation.AboutUsActivity;
import com.example.navigation_drawer.ProfileAndNavigation.AccountActivity;
import com.example.navigation_drawer.ProfileAndNavigation.ChatActivity;
import com.example.navigation_drawer.SurgicalProcedures.SurgicalProceduresDescriptions;

import java.util.List;
import java.util.concurrent.Executors;
/**
 * This is the main activity of the ElevateU app. It defines buttons to open two activities:
 * SurgicalProcedures and NonSurgicalProcedures. The class also includes methods for handling
 * the navigation drawer and navigating to other parts of the app.
 *
 * @version 1.0
 * @since 2024-04-25
 *
 * @author Beyza Arbaz
 * @author Lana Cvijic
 */
public class MainActivity extends AppCompatActivity {
    private Button openSurgicalProceduresActivity;
    private Button openNonSurgicalProceduresActivity;

    DrawerLayout drawer;

    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, and so on. onCreate method also initializes
     * the buttons and the navigation drawer.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     * Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDatabase db = MyDatabase.getDatabase(this);

        Executors.newSingleThreadExecutor().execute(() -> {
            // Check if doctors already exist
            List<Doctor> existingDoctors = db.doctorDao().getAllDoctors();
            if (existingDoctors.isEmpty()) {
                // Insert only if the list is empty
                db.doctorDao().insert(new Doctor(
                        getString(R.string.doctor_name_john_doe),
                        getString(R.string.doctor_specialty_plastic_surgeon),
                        getString(R.string.doctor_photo_url_1)
                ));

            }
        });

        drawer=findViewById(R.id.drawer_background);

        openSurgicalProceduresActivity = findViewById(R.id.surgical_button);
        openSurgicalProceduresActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent surgical_intent = new Intent(MainActivity.this, SurgicalProceduresDescriptions.class);
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

    /**
     * Handles the menu click event to open the navigation drawer.
     *
     * @param view The view that was clicked.
     */
    public void MenuClick (View view)
    {
        //Connection with method openTheDrawer
        openthedrawer(drawer);
    }

    /**
     * Opens the navigation drawer from the left side.
     *
     * @param drawer The DrawerLayout which is opened.
     */
    public static void openthedrawer(DrawerLayout drawer)
    {
        // Drawer to be opened from right
        drawer.openDrawer(GravityCompat.START);
    }

    /**
     * Handles the logo click event to close the navigation drawer.
     *
     * @param view The view that was clicked.
     */
    public void LogoClick(View view)
    {

        closethedrawer(drawer);
    }

    /**
     * Closes the navigation drawer if it is open.
     *
     * @param drawer The DrawerLayout to be closed.
     */
    public static void closethedrawer(DrawerLayout drawer)
    {
        //if drawer is open
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    /**
     * Handles the home page click event to recreate the current activity.
     *
     * @param view The view that was clicked.
     */
    public void HomePageClick (View view)
    {

        recreate();
    }

    /**
     * Handles the account click event to navigate to the account activity.
     *
     * @param view The view that was clicked.
     */
    public void AccountClick (View view)
    {
        //AccesofAccount
        Intent account = new Intent(MainActivity.this, AccountActivity.class);
        account.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(account);
        finish();

    }

    /**
     * Handles the chat click event to navigate to the chat activity.
     *
     * @param view The view that was clicked.
     */
    public void ChatClick (View view)
    {
        //Accesofchat
        Intent chat = new Intent(MainActivity.this, ChatActivity.class);
        chat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(chat);
        finish();
    }

    /**
     * Handles the about us click event to navigate to the about us activity.
     *
     * @param view The view that was clicked.
     */
    public void AboutUsClick (View view)
    {
        //Accesofchat
        Intent aboutUs = new Intent(MainActivity.this, AboutUsActivity.class);
        //finish Activity, dont come back
        aboutUs.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(aboutUs);
        finish();


    }

    /**
     * Handles the maps click event to navigate to the maps activity.
     *
     * @param view The view that was clicked.
     */
    public void MapsClick (View view)
    {
        //Accesofmaps
        Intent maps = new Intent(MainActivity.this, MapsActivity.class);
        //finish Activity, dont come back
        maps.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(maps);
        finish();


    }

    /**
     * Handles the exit click event to show a confirmation dialog and exit the app if confirmed.
     *
     * @param view The view that was clicked.
     */
    public void ExitClick(View view) {
        // Exit dialog
        AlertDialog.Builder exitDialog = new AlertDialog.Builder(MainActivity.this);

        // Set dialog title and message from resources
        exitDialog.setTitle(getString(R.string.exit_title));
        exitDialog.setMessage(getString(R.string.exit_message));

        // Set positive button with localized text and action
        exitDialog.setPositiveButton(getString(R.string.exit_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Exit app
                finishAffinity();
                System.exit(0);
            }
        });

        // Set negative button with localized text and action
        exitDialog.setNegativeButton(getString(R.string.exit_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Dismiss dialog
                dialogInterface.dismiss();
            }
        });

        // Show the dialog
        exitDialog.show();
    }


    /**
     * Called when the activity is paused. This method closes the navigation drawer if it is open.
     */
    protected void onPause(){

        closethedrawer(drawer);

        super.onPause();
    }

}