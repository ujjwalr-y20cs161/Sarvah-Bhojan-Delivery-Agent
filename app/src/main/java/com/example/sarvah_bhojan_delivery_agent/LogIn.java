package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private Button signIn,signUp,forgotPassword;
    private TextInputEditText email,password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        DynamicColors.applyToActivityIfAvailable(this);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();


        signIn = (Button) findViewById(R.id.SignIn);
        signUp = (Button) findViewById(R.id.SignUp);
        forgotPassword = (Button) findViewById(R.id.pswdfrgt);

        email = (TextInputEditText) findViewById(R.id.emailfield);
        password = (TextInputEditText)findViewById(R.id.passwordfield);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().equals("") && !password.getText().equals("")){
                    loginUser(email.getText().toString(),password.getText().toString());
                }else{
                    Toast.makeText(LogIn.this, "Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((!email.getText().equals("")) && (!password.getText().equals(""))){
                    registerUser(email.getText().toString(),password.getText().toString());
                }else{
                    Toast.makeText(LogIn.this, "Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().equals("")){
                    resetPassword(email.getText().toString());
                }else{
                    Toast.makeText(LogIn.this, "Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registerUser(String Email,String Pswd){
        mAuth.createUserWithEmailAndPassword(Email,Pswd).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(getApplicationContext(), "You have successfully registered!"+user.getEmail(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Landing.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Registration Unsuccessful, Try Again Later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void loginUser(String Email,String Pswd){
        mAuth.signInWithEmailAndPassword(Email,Pswd).addOnCompleteListener((task -> {
            if(task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(this, "Welcome Back! "+user.getEmail(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Landing.class);
                intent.putExtra("user",user);
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