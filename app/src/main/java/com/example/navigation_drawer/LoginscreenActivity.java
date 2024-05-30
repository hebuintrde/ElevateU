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

    EditText email, password;
    Button btnlogin;
    TextView txtnoaccount;
    DatabaseHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);


        email=findViewById(R.id.edit_email);
        password=findViewById(R.id.edit_password);
        btnlogin=findViewById(R.id.login_btn);
        txtnoaccount=findViewById(R.id.no_account);
        DB=new DatabaseHelper(this);


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

                String email_holder = email.getText().toString();
                String password_holder = password.getText().toString();

                if(email_holder.isEmpty()||password_holder.isEmpty())
                {
                    Toast.makeText(LoginscreenActivity.this,"Fields cannot be empty",Toast.LENGTH_LONG).show();

                }
                Boolean checkuserpass = DB.checkEmailPassword(email_holder, password_holder);
                if(checkuserpass == true){
                    Toast.makeText(LoginscreenActivity.this,"Login successful",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginscreenActivity.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                }

            }


    });

    }
}