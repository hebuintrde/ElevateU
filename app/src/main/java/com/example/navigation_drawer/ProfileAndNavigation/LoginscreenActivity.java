package com.example.navigation_drawer.ProfileAndNavigation;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private EditText editEmail, editPassword;
    private TextView noAccount;
    private Button loginButton;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_SUR_NAME = "surname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_BIRTHDAY = "birthday";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        noAccount = findViewById(R.id.no_account);
        loginButton = findViewById(R.id.login_btn);

        loginButton.setOnClickListener(v -> attemptLogin());
        noAccount.setOnClickListener(v -> startActivity(new Intent(this, SignupActivity.class)));

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }

    private void attemptLogin() {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_invalid_credentials), Toast.LENGTH_LONG).show();

            return;
        } else {
            authenticatePatient(email, password);
        }
    }

    private void authenticatePatient(String email, String password) {
        new Thread(() -> {
            MyDatabase db = MyDatabase.getDatabase(getApplicationContext());
            UserDao userDao = db.userDao();
            User user = userDao.findUserByEmailAndPassword(email, password);

            runOnUiThread(() -> {
                if (user != null) {
                    // Save user information in SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME, user.getFirstName());
                    editor.putString(KEY_SUR_NAME, user.getLastName());
                    editor.putString(KEY_EMAIL, user.getEmail());
                    editor.putString(KEY_BIRTHDAY, user.getBirthday());
                    editor.apply();

                    Toast.makeText(LoginscreenActivity.this, getString(R.string.toast_login_successful), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginscreenActivity.this, MainActivity.class);
                    intent.putExtra("user_id", email);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginscreenActivity.this, getString(R.string.toast_invalid_email_or_password), Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}


