package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.color.DynamicColors;

public class SignUp extends AppCompatActivity {

    private TextView activity_title,welcomefield;
    private String signinText,welcomegreet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        DynamicColors.applyToActivityIfAvailable(this);
        ActionBar actionBar = getSupportActionBar();
// Hide the action bar title and show only the app icon
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(getLocalClassName());
            actionBar.setDisplayShowHomeEnabled(true); // Optional: Enable the back button if needed
            actionBar.setIcon(R.mipmap.ic_launcher);
        }
        signinText = "Hello there!";
        welcomegreet = "Welcome \nPlease Enter Details to Sign up.";
        activity_title = (TextView) findViewById(R.id.activity_sign_in);
        welcomefield = (TextView) findViewById(R.id.welcomefield);
        activity_title.setText(signinText);
        welcomefield.setText(welcomegreet);


    }
}