package com.example.navigation_drawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginscreenActivity extends AppCompatActivity {

    EditText edtEmail, edtpassword;
    Button btnlogin;
    TextView txtnoaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);


        edtEmail=findViewById(R.id.edit_email);
        edtpassword=findViewById(R.id.edit_password);
        btnlogin=findViewById(R.id.login_btn);
        txtnoaccount=findViewById(R.id.no_account);

        txtnoaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginscreenActivity.this,SignupActivity.class)
                );
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email_holder = edtEmail.getText().toString();
                String password_holder = edtpassword.getText().toString();

                if(email_holder.isEmpty()||password_holder.isEmpty())
                {
                    Toast.makeText(LoginscreenActivity.this,"Fields cannot be empty",Toast.LENGTH_LONG).show();

                }
                else {
                    //if the fields are not empty = login

                }

            }
        });

    }
}