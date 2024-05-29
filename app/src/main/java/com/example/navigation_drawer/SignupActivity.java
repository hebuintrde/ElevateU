package com.example.navigation_drawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper DB;
    EditText edtName, edtSurname, edtBirthday, edtEmail, edtPassword, confirmPasswords;
    Button btnregis;
    TextView txtrAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);  // Ensure this matches your XML file name

        // Initialize views
        edtName = findViewById(R.id.edit_name);
        edtSurname = findViewById(R.id.edit_surname);
        edtBirthday = findViewById(R.id.edit_birthday);
        edtEmail = findViewById(R.id.edit_emailr);
        edtPassword = findViewById(R.id.edit_passwordr);
        confirmPasswords = findViewById(R.id.signupconfirm);
        btnregis = findViewById(R.id.register_btn);
        txtrAccount = findViewById(R.id.an_account);
        DB = new DatabaseHelper(this);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String surname = edtSurname.getText().toString();
                String birthday = edtBirthday.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = confirmPasswords.getText().toString();

                if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || birthday.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
                } else {
                    if (password.equals(confirmPassword)) {
                        Boolean checkUserEmail = DB.checkEmail(email);
                        if (!checkUserEmail) {
                            Boolean insert = DB.insertData(email, password);
                            if (insert) {
                                Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "User already exists, Please Login", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Invalid Password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        txtrAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginscreenActivity.class));
            }
        });
    }
}
