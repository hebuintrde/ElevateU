package com.example.navigation_drawer.ProfileAndNavigation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.navigation_drawer.MainActivity;
import com.example.navigation_drawer.Maps.MapsActivity;
import com.example.navigation_drawer.R;
/**
 * Activity for managing user account information and navigation options.
 *
 * <p>This activity displays the user's name and email retrieved from SharedPreferences.
 * It provides options to navigate to different parts of the application such as the main
 * activity, chat feature, about us section, and maps functionality. Users can also log out
 * using the exit button, which prompts a confirmation dialog.
 *
 * @version 1.0
 * @author Beyza Arbaz
 * @author Lana Cvijic
 */
public class AccountActivity extends AppCompatActivity {

    DrawerLayout drawer;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private TextView textName, textEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        drawer = findViewById(R.id.drawer_background);
        textName = findViewById(R.id.text_name);
        textEmail = findViewById(R.id.text_email);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // Retrieve and display user information
        String name = sharedPreferences.getString(KEY_NAME, "N/A");
        String email = sharedPreferences.getString(KEY_EMAIL, "N/A");
        textName.setText(name);
        textEmail.setText(email);
    }

    public void MenuClick(View view) {
        // Open the drawer
        MainActivity.openthedrawer(drawer);
    }

    public void LogoClick(View view) {
        // Close the drawer
        MainActivity.closethedrawer(drawer);
    }

    public void HomePageClick(View view) {
        // Switch to the Home Page
        Intent homepage = new Intent(AccountActivity.this, MainActivity.class);
        homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homepage);
        finish();
    }

    public void AccountClick(View view) {
        // Recreate the current activity
        recreate();
    }

    public void ChatClick(View view) {
        // Switch to the Chat Activity
        Intent chat = new Intent(AccountActivity.this, ChatActivity.class);
        chat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chat);
        finish();
    }

    public void AboutUsClick(View view) {
        // Switch to the About Us Activity
        Intent aboutUs = new Intent(AccountActivity.this, AboutUsActivity.class);
        aboutUs.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(aboutUs);
        finish();
    }

    public void MapsClick(View view) {
        // Switch to the Maps Activity
        Intent maps = new Intent(AccountActivity.this, MapsActivity.class);
        maps.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(maps);
        finish();
    }

    public void ExitClick(View view) {
        // Show exit confirmation dialog
        AlertDialog.Builder warningWindow = new AlertDialog.Builder(AccountActivity.this);
        warningWindow.setTitle("Exit");
        warningWindow.setMessage("Are you sure you want to sign out?");

        warningWindow.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                System.exit(0);
            }
        });

        warningWindow.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        warningWindow.show();
    }

    @Override
    protected void onPause() {
        // Close the drawer when the activity is paused
        MainActivity.closethedrawer(drawer);
        super.onPause();
    }
}
