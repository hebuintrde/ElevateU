package com.example.navigation_drawer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.navigation_drawer.User.User;
import com.example.navigation_drawer.User.UserViewModel;

public class SignupActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private EditText editName, editSurname, editBirthday, editEmail, editPassword, editConfirmPassword;
    private TextView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        editName = findViewById(R.id.edit_name);
        editSurname = findViewById(R.id.edit_surname);
        editBirthday = findViewById(R.id.edit_birthday);
        editEmail = findViewById(R.id.edit_emailr);
        editPassword = findViewById(R.id.edit_passwordr);
        editConfirmPassword = findViewById(R.id.signupconfirm);
        account = findViewById(R.id.an_account);

        findViewById(R.id.register_btn).setOnClickListener(v -> registerUser());

        // Set up click listener for the "I have an account" TextView
        account.setOnClickListener(v -> navigateToLogin());
    }

    private void registerUser() {
        String name = editName.getText().toString();
        String surname = editSurname.getText().toString();
        String birthday = editBirthday.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();
        String anaccount = account.getText().toString();

        if (!validateInput(name, surname, birthday, email, password, confirmPassword)) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        userViewModel.isEmailExists(email).observe(this, emailExists -> {
            if (emailExists != null && emailExists > 0) {
                Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User();
                user.setFirstName(name);
                user.setLastName(surname);
                user.setBirthday(birthday);
                user.setEmail(email);
                user.setPassword(password);

                userViewModel.insert(user);
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

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

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginscreenActivity.class);
        startActivity(intent);
    }
}
