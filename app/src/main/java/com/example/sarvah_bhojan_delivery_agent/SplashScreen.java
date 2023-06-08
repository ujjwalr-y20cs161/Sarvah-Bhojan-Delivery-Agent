package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;


public class SplashScreen extends AppCompatActivity {
    private TextView Message;
    private static final  int SPLASH_DELAY = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.getSupportActionBar().hide();
        Message = (TextView) findViewById(R.id.app_title);
        Message.setText("Welcome to "+getText(R.string.app_name));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity
                Agent user = UserSession.getInstance().getUser();
                if (user == null) {
                    // User is not logged in, perform necessary actions

                    Intent intent = new Intent(getApplicationContext(), LogIn.class);
                    startActivity(intent);

                    // Finish the splash activity
                    finish();
                } else {
                    // User is logged in, handle accordingly

                    Intent intent = new Intent(getApplicationContext(), Landing.class);
                    startActivity(intent);

                    // Finish the splash activity
                    finish();
                }
            }
        }, SPLASH_DELAY);
    }

}