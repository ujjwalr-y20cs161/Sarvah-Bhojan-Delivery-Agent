package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

public class Landing extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
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
    }
}