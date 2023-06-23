package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        TextView FullName,Email,TimeStamp,Age,Gender;
        FullName = findViewById(R.id.fullname);
        Email = findViewById(R.id.email);
        TimeStamp = findViewById(R.id.joined);
        Age = findViewById(R.id.Age);
        Gender = findViewById(R.id.Gender);

        Button editInfo,confirm;
        editInfo = findViewById(R.id.edit);
        confirm = findViewById(R.id.Confirm);


        TextInputEditText FirstName,LastName,AgeField;

        MaterialAutoCompleteTextView genderField = findViewById(R.id.genderfield);
//        FirstName = findViewById(R.id.firstnamefield);
//        LastName = findViewById(R.id.lastnamefield);
//        AgeField = findViewById(R.id.agefield);

        CardView editCard = findViewById(R.id.edit_card);
        Agent agent = UserSession.getInstance().getUser();

        FullName.setText(agent.getFirstName()+" "+agent.lastName);
//        Email.setText(agent.getEmailId());
//        if(agent.getJoined()==null) TimeStamp.setText("Joined In");
//        else TimeStamp.setText(new SimpleDateFormat("yy-MM-DD").format(agent.getJoined()));
//        Age.setText(agent.age);

        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCard.setVisibility(View.VISIBLE);
//                FirstName.setText(agent.firstName);
//                LastName.setText(agent.lastName);
//                AgeField.setText(agent.age);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TODO:get the info from fields and update agent object in Usersession and also in firebase DB
                Toast.makeText(Profile.this, "Info Updated", Toast.LENGTH_SHORT).show();
            }
        });




    }
}