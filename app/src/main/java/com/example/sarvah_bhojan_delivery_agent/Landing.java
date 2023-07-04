package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.Manifest;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;

import java.util.Calendar;

public class Landing extends AppCompatActivity {
    private TextView textGreeting;
    private TextView textName;
    private TextView textEmail;

    public MyApp myapp;
    public String ScreenTitle = "Sarva Bhojan\nDelivery Agent";
    public Button active;
    private static final int REQUEST_LOCATION_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        //Dynamic Colors to applied on the screen
        DynamicColors.applyToActivityIfAvailable(this);
        getSupportActionBar().hide();

        // Back button click listener
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
//                onBackPressed();
                Toast.makeText(Landing.this, "Hello!!", Toast.LENGTH_SHORT).show();
            }
        });

// Settings button click listener
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this,Settings.class);
                startActivity(intent);
            }
        });

        active = (Button)findViewById(R.id.active);
        TextView actionBarText = findViewById(R.id.actionBarText);
        actionBarText.setText(this.ScreenTitle);
        // Reference the TextViews
        textGreeting = findViewById(R.id.text_greeting);
        textName = findViewById(R.id.text_name);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.baseline_circle_24);
        int color = ContextCompat.getColor(getApplicationContext(), R.color.denyred);

        if (drawable != null) {
            drawable.setTint(color);
            drawable.invalidateSelf();
        }

//      Retrieving SavedInstance of User
        Agent myAgent = UserSession.getInstance().getUser();
        if(myAgent!=null) {
//           Populating UI with Agent Info
            textGreeting.setText(getGreeting());
            textName.setText(myAgent.getFirstName());

        }
        else{
//            if any error, this will clean the shared preferences and reset the session.
            Toast.makeText(this, "Error Encountered!\nPlease Logout and Login Again.", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove("LoggedIn");
            editor.remove("AgentID");
            editor.apply();
        }

        // Check if the location permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted, proceed with location access
            // ...
        } else {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }

//        Logout ClickListener


//Makes Agent Visible in the server
        active.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Should send, active call to Server and the current location of the agent:
//               Server Calls.
                int color = ContextCompat.getColor(getApplicationContext(), R.color.green);
                if (drawable != null) {
                    drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    drawable.invalidateSelf();
                }
                startActivity(new Intent(getApplicationContext(), OrderScreen.class));
            }
        }));

        CardView Orders;
        TextView Earning,Uptime;
        Earning = findViewById(R.id.More);
        Earning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Landing.this, OrderHistory.class));
            }
        });

        Uptime = findViewById(R.id.SeeLog);
        Uptime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Landing.this, SessionLog.class));
            }
        });

        Orders = findViewById(R.id.order_card);
        Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Landing.this, OrderHistory.class));
            }
        });
    }

    public String getGreeting() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (hourOfDay >= 5 && hourOfDay < 12) {
            return "Good morning 🌅";
        } else if (hourOfDay >= 12 && hourOfDay < 17) {
            return "Good afternoon ☀️";
        } else if (hourOfDay >= 17 && hourOfDay < 19) {
            return "Good evening 🌇";
        } else {
            return "Good night 🌃";
        }
    }

}
