package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {

    private Button signIn,signUp,forgotPassword;
    private TextInputEditText email,password;
    private TextView LoginText;
    private String loginText;
    private FirebaseAuth mAuth;
    public  MyApp myapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        DynamicColors.applyToActivityIfAvailable(this);
        ActionBar actionBar = getSupportActionBar();
// Hide the action bar title and show only the app icon
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(getLocalClassName());
            actionBar.setDisplayShowHomeEnabled(true); // Optional: Enable the back button if needed
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

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
                    loginUser(email.getText().toString(),password.getText().toString());
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
                UserSession.getInstance().setUser(new Agent(Email,Pswd));
                Agent myagent = UserSession.getInstance().getUser();
                Toast.makeText(this,myagent.getEmailId(), Toast.LENGTH_SHORT).show();
                FirebaseUser user = mAuth.getCurrentUser();
//                Toast.makeText(this, "Welcome Back! "+user.getEmail(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Landing.class);
//                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Login Unsuccessful, Try Again!", Toast.LENGTH_SHORT).show();
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