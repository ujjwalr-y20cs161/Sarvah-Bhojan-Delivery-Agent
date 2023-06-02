package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class Landing extends AppCompatActivity {
    private TextInputEditText ResponseText;
    public String urlString = "https://sbdaapi.free.beeceptor.com/demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        DynamicColors.applyToActivityIfAvailable(this);
        ActionBar actionBar = getSupportActionBar();
// Hide the action bar title and show only the app icon
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(getLocalClassName());
            actionBar.setDisplayShowHomeEnabled(true); // Optional: Enable the back button if needed
            actionBar.setIcon(R.mipmap.ic_launcher);
        }
//        FirebaseUser user = (FirebaseUser) getIntent().getExtras().getParcelable("user");
//        try{
//            if(user!=null) {
//                Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, "UserNull", Toast.LENGTH_SHORT).show();
//            }
//        }
//        catch (Exception e){
//            Toast.makeText(this, "Floppy", Toast.LENGTH_SHORT).show();
//        }
//
//
//

        String name =  "Hello";
        // URL of the server
        ResponseText = (TextInputEditText) findViewById(R.id.ResponseText);
        ResponseText.setText(name);
    }

}
