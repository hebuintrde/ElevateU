package com.example.navigation_drawer.ProfileAndNavigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.navigation_drawer.MainActivity;
import com.example.navigation_drawer.R;
import com.example.navigation_drawer.User.User;
import com.example.navigation_drawer.User.UserViewModel;
/**
 * Activity for user registration.
 * This activity allows new users to register by providing their name, lastname, date of birth,
 * email, and password. It validates user inputs, checks for existing email addresses,
 * and saves user information locally using SharedPreferences. Upon successful registration,
 * the user is redirected to the {@link MainActivity}.
 *
 * @version 1.0
 * @author Beyza Arbaz
 * @author Lana Cvijic
 */
public class SignupActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private EditText editName, editSurname, editBirthday, editEmail, editPassword, editConfirmPassword;
    private TextView account;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    /**
     * Initializes the activity layout and sets up views, ViewModel, and click listeners.
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize ViewMode
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Initialize views
        editName = findViewById(R.id.edit_name);
        editSurname = findViewById(R.id.edit_surname);
        editBirthday = findViewById(R.id.edit_birthday);
        editEmail = findViewById(R.id.edit_emailr);
        editPassword = findViewById(R.id.edit_passwordr);
        editConfirmPassword = findViewById(R.id.signupconfirm);
        account = findViewById(R.id.an_account);

        // Set up click listener for the Register button
        findViewById(R.id.register_btn).setOnClickListener(v -> registerUser());

        // Initialize SharedPreferences for storing user information
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // Set up click listener for the "I have an account" TextView
        account.setOnClickListener(v -> navigateToLogin());
    }

    /**
     * Validates user input and initiates the registration process.
     * Displays toast messages for validation errors.
     */
    private void registerUser() {
        String name = editName.getText().toString();
        String surname = editSurname.getText().toString();
        String birthday = editBirthday.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();

        if (!validateInput(name, surname, birthday, email, password, confirmPassword)) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the email is already registered
        userViewModel.isEmailExists(email).observe(this, emailExists -> {
            if (emailExists != null && emailExists > 0) {
                Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
            } else {
                // Create a new User object and insert it into the database
                User user = new User();
                user.setFirstName(name);
                user.setLastName(surname);
                user.setBirthday(birthday);
                user.setEmail(email);
                user.setPassword(password);

                userViewModel.insert(user);
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show();

                // Save user information in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, name);
                editor.putString(KEY_EMAIL, email);
                editor.apply();

                // Finish the activity and navigate to MainActivity
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Validates the user input fields.
     *
     * @param name            User's first name.
     * @param surname         User's last name.
     * @param birthdate       User's birthday in the format DD.MM.YYYY.
     * @param email           User's email address.
     * @param password        User's password.
     * @param confirmPassword User's password confirmation.
     * @return {@code true} if all fields are valid, otherwise {@code false}.
     */
    private boolean validateInput(String name, String surname, String birthdate, String email, String password, String confirmPassword) {
        if (name.isEmpty() || surname.isEmpty() || birthdate.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!birthdate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            Toast.makeText(this, "Birthdate must be in the format DD.MM.YYYY", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * Navigates to the login screen activity.
     */    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginscreenActivity.class);
        startActivity(intent);
    }
}
