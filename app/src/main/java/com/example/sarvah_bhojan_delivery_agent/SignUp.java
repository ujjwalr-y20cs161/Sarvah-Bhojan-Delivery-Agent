package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {

    private TextView activity_title, welcomefield;
    private String signinText, welcomegreet;
    private Button signInButton;
    private Agent agent;
    private FirebaseAuth mAuth;
    public String ScreenTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        DynamicColors.applyToActivityIfAvailable(this);
        getSupportActionBar().hide();

        ImageButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ScreenTitle = "Sign Up Page";
// Hide the action bar title and show only the app icon
        TextView actionBarText = findViewById(R.id.actionBarText);
        actionBarText.setText(this.ScreenTitle);

        signinText = "Hello there!";
        welcomegreet = "Welcome \nPlease Enter Details to Sign up.";
        activity_title = (TextView) findViewById(R.id.activity_sign_in);
        welcomefield = (TextView) findViewById(R.id.welcomefield);
        activity_title.setText(signinText);
        welcomefield.setText(welcomegreet);

        signInButton = findViewById(R.id.cont_sign_in);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email, password, and confirm password entered by the user
                TextInputEditText firstNameEditText = findViewById(R.id.firstnamefield);
                TextInputEditText lastNameEditText = findViewById(R.id.lastnamefield);
                TextInputEditText ageEditText = findViewById(R.id.agefield);
                MaterialAutoCompleteTextView genderField = findViewById(R.id.genderfield);
                TextInputLayout menu = (TextInputLayout) findViewById(R.id.menu);

                TextInputEditText emailEditText = findViewById(R.id.emailfield);
                TextInputEditText passwordEditText = findViewById(R.id.passwordfield);
                TextInputEditText confirmPasswordEditText = findViewById(R.id.confirm_passwordfield);

                String firstName = firstNameEditText.getText().toString().trim();
                String lastName = lastNameEditText.getText().toString().trim();
                String ageString = ageEditText.getText().toString().trim();
                String selectedGender = genderField.getText().toString();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Validate first name
                if (firstName.isEmpty()) {
                    firstNameEditText.setError("First name is required");
                    firstNameEditText.requestFocus();
                    return;
                }

                if (lastName.isEmpty()) {
                    lastNameEditText.setError("Last name is required");
                    lastNameEditText.requestFocus();
                    return;
                }

                // Validate age
                if (ageString.isEmpty()) {
                    ageEditText.setError("Age is required");
                    ageEditText.requestFocus();
                    return;
                }
                int age = Integer.parseInt(ageString);
                if (age <= 17) {
                    ageEditText.setError("Should be major");
                    ageEditText.requestFocus();
                    return;
                }

                if (selectedGender.isEmpty()) {
                    // No gender selected
                    menu.setError("Gender Should be Selected");
                    menu.requestFocus();
                    return;
                }

                // Validate email
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEditText.setError("Invalid email address");
                    emailEditText.requestFocus();
                    return;
                }

                // Validate password
                if (password.isEmpty()) {
                    passwordEditText.setError("Password is required");
                    passwordEditText.requestFocus();
                    return;
                }

                // Validate confirm password
                if (!password.equals(confirmPassword)) {
                    confirmPasswordEditText.setError("Passwords do not match");
                    confirmPasswordEditText.requestFocus();
                    return;
                }

                agent = new Agent(firstName,lastName,selectedGender,email,password,age);


//              Disable the button while processing
                signInButton.setEnabled(false);
                // All fields are valid, continue with your login logic here
                registerUser(agent);

            }
        });
    }

    public void registerUser(Agent agent){
        mAuth.createUserWithEmailAndPassword(agent.getEmailId(),agent.getPassword()).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

//              Add uid to agentObject
                FirebaseUser user = mAuth.getCurrentUser();
                agent.setUid(user.getUid());

//              Store in Firebase Realtime Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference agentsRef = database.getReference("agents");
                DatabaseReference agentRef = agentsRef.push();
                agentRef.setValue(agent);

//              Keep in UserSession Instance
                UserSession.getInstance().setUser(agent);

//              Toggle SharedReference and Store AgentId
                SharedPreferences sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("LoggedIn",true);
                editor.putString("AgentID",agent.getUid());
                editor.apply();

//              Intents and Toasts
                Toast.makeText(getApplicationContext(), "You have successfully registered!"+user.getEmail(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Landing.class);
                startActivity(intent);
                finishAndRemoveTask();
            }else{
                Toast.makeText(this, "Registration Unsuccessful, Try Again Later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}