package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;

import java.util.Calendar;

public class Landing extends AppCompatActivity {
    private TextView textGreeting;
    private TextView textName;
    private TextView textEmail;

    public MyApp myapp;
    public String ScreenTitle = "Home";
    private Button logout,active;
    private static final int REQUEST_LOCATION_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        DynamicColors.applyToActivityIfAvailable(this);
        ActionBar actionBar = getSupportActionBar();

// Hide the action bar title and show only the app icon
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(this.ScreenTitle);
            actionBar.setDisplayShowHomeEnabled(true); // Optional: Enable the back button if needed
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

        logout = (Button) findViewById(R.id.logout);
        active = (Button)findViewById(R.id.active);
        // Reference the TextViews
        textGreeting = findViewById(R.id.text_greeting);
        textName = findViewById(R.id.text_name);
        textEmail = findViewById(R.id.text_email);


        Agent myAgent = UserSession.getInstance().getUser();
        if(myAgent!=null) {
            textGreeting.setText(getGreeting()+" "+MyApp.toTitleCase(myAgent.getFirstName())+"!");
            Toast.makeText(this, myAgent.getFirstName(), Toast.LENGTH_SHORT).show();
            textEmail.setText("Email : "+myAgent.getEmailId());
            textName.setText("Name : "+MyApp.toTitleCase(myAgent.getFirstName()+" "+myAgent.lastName));
        }
        else{
//            if any error, this will clean the shared preferences and reset the session.
            Toast.makeText(this, "Fuxxed Up", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("LoggedIn",false);
            editor.putString("AgentID","");
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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Opens a Dialog Box to Confirm Logout
                Logout();
            }
        });

//Makes Agent Visible in the server
        active.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Should send, active call to Server and the current location of the agent:
//               Server Calls.
                startActivity(new Intent(getApplicationContext(), OrderScreen.class));
            }
        }));
    }

    public String getGreeting() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (hourOfDay >= 5 && hourOfDay < 12) {
            return "Good morning 🌅\n";
        } else if (hourOfDay >= 12 && hourOfDay < 17) {
            return "Good afternoon ☀️\n";
        } else if (hourOfDay >= 17 && hourOfDay < 19) {
            return "Good evening 🌇\n";
        } else {
            return "Good night 🌃\n";
        }
    }

    public void Logout(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Landing.this);
        builder.setMessage("Do you want to Logout?");

        builder.setTitle("Confirm Logout");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
            UserSession.getInstance().clearSession();
            SharedPreferences sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove("LoggedIn");
            editor.remove("AgentID");
            editor.apply();
            startActivity(new Intent(getApplicationContext(), LogIn.class));
            finish();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
