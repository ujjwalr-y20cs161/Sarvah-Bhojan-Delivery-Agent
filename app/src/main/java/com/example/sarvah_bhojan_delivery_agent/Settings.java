package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;

public class Settings extends AppCompatActivity {

    private String ScreenTitle = "Profile & Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        DynamicColors.applyToActivityIfAvailable(this);
        getSupportActionBar().hide();

        Button logout = findViewById(R.id.settings_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Opens a Dialog Box to Confirm Logout
                Logout();
            }
        });

        ImageButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TextView Payment,Appearance,About,Orders,Tutorials,actionBarText;

        actionBarText = findViewById(R.id.actionBarText);
        actionBarText.setText(this.ScreenTitle);

    }


    public void Logout(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        builder.setMessage("Do you want to Logout?");

        builder.setTitle("Confirm Logout");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
//           Clear the saved agent Info
            UserSession.getInstance().clearSession();
//            Clear the sharedPreferences
            SharedPreferences sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove("LoggedIn");
            editor.remove("AgentID");
            editor.apply();
//            Redirect to Login screen
            startActivity(new Intent(getApplicationContext(), LogIn.class));
//            Remove current Screen from history stack
            finish();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}