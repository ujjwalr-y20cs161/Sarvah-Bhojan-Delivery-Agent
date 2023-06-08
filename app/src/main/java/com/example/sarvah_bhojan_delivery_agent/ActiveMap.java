package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;


public class ActiveMap extends AppCompatActivity {

    private String ScreenTitle = "Map Screen";
    private int noBacktaps = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_map);

        DynamicColors.applyToActivityIfAvailable(this);
        ActionBar actionBar = getSupportActionBar();

// Hide the action bar title and show only the app icon
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(this.ScreenTitle);
            actionBar.setDisplayShowHomeEnabled(true); // Optional: Enable the back button if needed
            actionBar.setIcon(R.mipmap.ic_launcher);
        }
        // Initialize the MapView
    }

    @Override
    public void onBackPressed() {
        if(noBacktaps<1){
            noBacktaps++;
            Toast.makeText(this, "Press Again, to go back", Toast.LENGTH_SHORT).show();
        }else{
            noBacktaps = 0;
            startActivity(new Intent(this,Landing.class));
            finish();
        }
    }
}