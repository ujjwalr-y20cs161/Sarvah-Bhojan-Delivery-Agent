package com.example.sarvah_bhojan_delivery_agent;


import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.content.DialogInterface;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.google.android.material.color.DynamicColors;

import java.util.ArrayList;
import java.util.List;


public class OrderScreen extends AppCompatActivity {

    private String ScreenTitle = "Map Screen";
    private int noBacktaps = 0;

    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private List<Order> data;
    private Button deactive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        DynamicColors.applyToActivityIfAvailable(this);
        ActionBar actionBar = getSupportActionBar();

// Hide the action bar title and show only the app icon
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(this.ScreenTitle);
            actionBar.setDisplayShowHomeEnabled(true); // Optional: Enable the back button if needed
            actionBar.setIcon(R.mipmap.ic_launcher);
        }
        // Initialize the Views
        deactive = findViewById(R.id.Deactivate);
        // Initialize data
        data = new ArrayList<>();
//        static init --
//        Server sends data should use server calls for data
        data.add(new Order("1001", "Alex", "jkbfgorybco", "ParkAvenue 4th Cross", "Alex Station,Las Vegas", null, null, 80, 4));
        data.add(new Order("1002", "Nolan", "ajerbvnaruv", "ParkAvenue 4th Cross", "WB Studios", null, null, 100, 5));
        data.add(new Order("1003", "Bale", "asjdvunero", "North Corner Restaurant", "Universal Studios", null, null, 400, 20));

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new OrderAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        deactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitScreen();
            }
        });

    }

    @Override
    public void onBackPressed() {
        ExitScreen();
    }

    public void ExitScreen(){
//        Opens a dialog box to confirm exit

        AlertDialog.Builder builder = new AlertDialog.Builder(OrderScreen.this);
        builder.setMessage("Do you want to exit ?\nBy exiting orders will be cancelled.");

        builder.setTitle("Confirm exit");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}