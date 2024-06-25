package com.example.navigation_drawer.ProfileAndNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigation_drawer.MainActivity;
import com.example.navigation_drawer.MyDatabase;
import com.example.navigation_drawer.R;
import com.example.navigation_drawer.User.User;
import com.example.navigation_drawer.User.UserDao;
import com.example.navigation_drawer.User.UserViewModel;

/**
 * Activity for handling user login functionality.
 * This activity allows users to log in using their email and password.
 * It validates user input and authenticates against stored credentials in the local database.
 * Upon successful login, the user is redirected to the {@link MainActivity}.
 *
 * @version 1.0
 * @author Beyza Arbaz
 * @author Lana Cvijic
 */
public class LoginscreenActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private EditText editEmail, editPassword;
    private TextView noAccount;
    private Button loginButton;


    /**
     * Initializes the activity layout and sets up views and click listeners.
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        // Initialize views
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        noAccount = findViewById(R.id.no_account);
        loginButton = findViewById(R.id.login_btn);

        // Set click listeners
        loginButton.setOnClickListener(v -> attemptLogin());
        noAccount.setOnClickListener(v -> startActivity(new Intent(this, SignupActivity.class)));


    }

    /**
     * Attempts to log in the user using the provided email and password.
     * Displays a toast message if credentials are missing or incorrect.
     */
    private void attemptLogin() {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_LONG).show();
            return;
        } else {

            authenticatePatient(email, password);


        }
    }

    /**
     * Authenticates the user asynchronously against the local database.
     * Displays a toast message indicating login success or failure.
     *
     * @param email    The email entered by the user.
     * @param password The password entered by the user.
     */
    private void authenticatePatient(String email, String password) {
        new Thread(() -> {
            MyDatabase db = MyDatabase.getDatabase(getApplicationContext());
            UserDao userDao = db.userDao();
            User user = userDao.findUserByEmailAndPassword(email, password);

            runOnUiThread(() -> {
                if (user != null) {
                    Toast.makeText(LoginscreenActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginscreenActivity.this, MainActivity.class);
                    intent.putExtra("user_id", email);  // mit parseInt Methode bekommt man ein int zuruck und das dient als unique ID
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginscreenActivity.this, "Invalid email number or password", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}


