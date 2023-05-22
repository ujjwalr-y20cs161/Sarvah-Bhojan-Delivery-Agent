package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class SplashScreen extends AppCompatActivity {
    TextView Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.getSupportActionBar().hide();
        Message = (TextView) findViewById(R.id.app_title);
        Message.setText("Welcome to "+getText(R.string.app_name));

        startActivity( new Intent(getApplicationContext(),LogIn.class));
        finish();

    }

}