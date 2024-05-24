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

public class SignupActivity extends AppCompatActivity {

    EditText edtName, edtSurname, edtBirthday, edtEmail, edtPassword;
    Button btnregis;
    TextView txtrAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtName=findViewById(R.id.edit_name);
        edtSurname=findViewById(R.id.edit_surname);
        edtBirthday=findViewById(R.id.edit_birthday);
        edtEmail=findViewById(R.id.edit_emailr);
        edtPassword=findViewById(R.id.edit_passwordr);
        btnregis=findViewById(R.id.register_btn);
        txtrAccount=findViewById(R.id.an_account);

        txtrAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(SignupActivity.this,LoginscreenActivity.class)));
            }
        });


        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_holder = edtName.getText().toString();
                String surname_holder = edtSurname.getText().toString();
                String birthday_holder = edtBirthday.getText().toString();
                String email_holder = edtEmail.getText().toString();
                String password_holder = edtPassword.getText().toString();

                if(name_holder.isEmpty()|surname_holder.isEmpty()||email_holder.isEmpty()||password_holder.isEmpty()||birthday_holder.isEmpty())
                {
                    Toast.makeText(SignupActivity.this,"Fields cannot be empty",Toast.LENGTH_LONG).show();

                }
                else {
                    //if the fields are not empty = register

                }

            }
        });

    }


}