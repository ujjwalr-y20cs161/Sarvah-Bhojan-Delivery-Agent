package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private Button signIn,signUp,forgotPassword;
    private TextInputEditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        DynamicColors.applyToActivityIfAvailable(this);

        FirebaseApp.initializeApp(this);

        signIn = (Button) findViewById(R.id.SignIn);
        signUp = (Button) findViewById(R.id.SignUp);
        forgotPassword = (Button) findViewById(R.id.pswdfrgt);

        email = (TextInputEditText) findViewById(R.id.emailfield);
        password = (TextInputEditText)findViewById(R.id.passwordfield);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(LogIn.this, email.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LogIn.this, password.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LogIn.this, "OTP is sent to your mail!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}