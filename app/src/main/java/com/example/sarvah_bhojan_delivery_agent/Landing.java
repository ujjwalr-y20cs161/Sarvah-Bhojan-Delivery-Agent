package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    public MyApp myapp;
    public String ScreenTitle = "Home";
    private Button logout;
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


        Agent myAgent = UserSession.getInstance().getUser();
        if(myAgent!=null){
            Toast.makeText(this, myAgent.getFirstName(), Toast.LENGTH_SHORT).show();
            ResponseText = (TextInputEditText) findViewById(R.id.ResponseText);
            ResponseText.setText(myAgent.firstName+" "+myAgent.gender);
        }
        else{
            Toast.makeText(this, "Fuxxed Up", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("LoggedIn",false);
            editor.putString("AgentID","");
            editor.apply();
        }



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                UserSession.getInstance().clearSession();
                SharedPreferences sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("LoggedIn");
                editor.remove("AgentID");
                editor.apply();
                startActivity(new Intent(getApplicationContext(), LogIn.class));
                finish();
            }
        });
    }

}
