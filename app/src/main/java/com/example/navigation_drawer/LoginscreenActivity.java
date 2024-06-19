package com.example.navigation_drawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.navigation_drawer.User.User;
import com.example.navigation_drawer.User.UserDao;
import com.example.navigation_drawer.User.UserViewModel;

public class LoginscreenActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private EditText editEmail, editPassword;
    private TextView noAccount;
    private Button loginButton;


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


    }

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


