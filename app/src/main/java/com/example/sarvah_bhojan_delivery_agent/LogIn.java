package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {

    private Button signIn,signUp,forgotPassword;
    private TextInputEditText email,password;
    private TextView LoginText;
    private String loginText,ScreenTitle;
    private FirebaseAuth mAuth;
    public  MyApp myapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        DynamicColors.applyToActivityIfAvailable(this);
        getSupportActionBar().hide();
        ScreenTitle = "Log In Page";

        myapp=(MyApp)getApplicationContext();

        loginText ="Welcome to \n"+getString(R.string.app_name);
        LoginText = findViewById(R.id.Logintext);
        LoginText.setText(loginText);


        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();


        signIn = (Button) findViewById(R.id.sign_in);
        signUp = (Button) findViewById(R.id.SignUp);
        forgotPassword = (Button) findViewById(R.id.pswdfrgt);

        email = (TextInputEditText) findViewById(R.id.emailfield);
        password = (TextInputEditText)findViewById(R.id.passwordfield);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    signIn.setEnabled(false);
                    signUp.setEnabled(false);
                    forgotPassword.setEnabled(false);
//                    Toasts
                    loginUser(email.getText().toString(),password.getText().toString());
//                    While processing Disable button

                }else{
                    if(email.getText().toString().isEmpty()) {
                        email.setError("Email Id is required");
                        email.requestFocus();
                    }
                    if(password.getText().toString().isEmpty()){
                        password.setError(("Password is required"));
                        password.requestFocus();
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Disable buttons while processing
                signUp.setEnabled(false);
                signIn.setEnabled(false);
                forgotPassword.setEnabled(false);

//                Intent call
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    resetPassword(email.getText().toString());
                }else{
                    email.setError("Enter Valid Credentials");
                }
            }
        });
    }



    public  void loginUser(String Email,String Pswd){
        mAuth.signInWithEmailAndPassword(Email,Pswd).addOnCompleteListener((task -> {
            if(task.isSuccessful()){
//               Create an Agent with following Email and Password
                FirebaseUser user = mAuth.getCurrentUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference agentsRef = database.getReference("agents");
                DatabaseReference agentRef = agentsRef.push();
                Toast.makeText(LogIn.this, "Logging you in!", Toast.LENGTH_SHORT).show();
                agentsRef.orderByChild("uid").equalTo(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot agentSnapshot : dataSnapshot.getChildren()) {
//                        Get agent object from RTDB
                            Agent agent = agentSnapshot.getValue(Agent.class);
                            Log.e("Agent",agent.getEmailId());
                            // Set as the current instance
                            UserSession.getInstance().setUser(agent);
                            Log.d("UserSession","Instance Has been set");

//                          Change Shared Preferences
                            SharedPreferences sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean("LoggedIn",true);
                            editor.putString("AgentID",user.getUid());
                            editor.apply();
//                        move to landing page
                            startActivity(new Intent(getApplicationContext(),Landing.class));
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle the error
                        Toast.makeText(myapp, "Failed, Close App", Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                Toast.makeText(this, "Login Unsuccessful, Try Again!", Toast.LENGTH_SHORT).show();
                signIn.setEnabled(true);
                signUp.setEnabled(true);
                forgotPassword.setEnabled(true);
            }
        }));
    }

    public void resetPassword(String Email){
        mAuth.sendPasswordResetEmail(Email).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(LogIn.this, "Password reset link is sent to your mail!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Unsuccessful Request,Try Again!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}