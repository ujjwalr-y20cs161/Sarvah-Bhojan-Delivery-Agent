package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.textfield.TextInputEditText;

public class LogIn extends AppCompatActivity {

    private Button signIn,signUp,forgotPassword;
    private TextInputEditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        DynamicColors.applyToActivityIfAvailable(this);

        signIn = (Button) findViewById(R.id.SignIn);
        signUp = (Button) findViewById(R.id.SignUp);
        forgotPassword = (Button) findViewById(R.id.pswdfrgt);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}