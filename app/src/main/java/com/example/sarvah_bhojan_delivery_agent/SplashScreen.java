package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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

//        Checks SharedPreference if LoggedIn or not

        SharedPreferences sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE);

        boolean isLoggedIn = sharedPref.getBoolean("LoggedIn", false);
        String agentId = sharedPref.getString("AgentID"," ");
        if(isLoggedIn){

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference agentsRef = database.getReference("agents");
            DatabaseReference agentRef = agentsRef.push();
            agentsRef.orderByChild("uid").equalTo(agentId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot agentSnapshot : dataSnapshot.getChildren()) {
//                        Get agent object from RTDB
                        Agent agent = agentSnapshot.getValue(Agent.class);
                        Log.e("Agent",agent.getEmailId());
                        // Set as the current instance
                        UserSession.getInstance().setUser(agent);
                        Log.d("UserSession","Instance Has been set");
//                        move to landing page
                        startActivity(new Intent(getApplicationContext(),Landing.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle the error
                    Toast.makeText(getApplicationContext(), "Invalid State, Reopen the App", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.remove("LoggedIn");
                    editor.remove("AgentID");
                    editor.apply();
                    Log.e("SharedPreference","Corrupted Login");

                }
            });

        }
        else{
            startActivity(new Intent(this, LogIn.class));
            finish();
        }

    }

}