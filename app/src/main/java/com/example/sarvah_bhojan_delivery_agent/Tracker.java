package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.color.DynamicColors;

import org.w3c.dom.Text;

public class Tracker extends AppCompatActivity {

    private String ScreenTitle = "Order Tracker",Details;
    private TextView OrderDetails;
    private Button pickUp,dropTo,delivered,cancel;
    private WebView webView;
    private Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        DynamicColors.applyToActivityIfAvailable(this);
        getSupportActionBar().hide();
        TextView actionBarText = findViewById(R.id.actionBarText);
        actionBarText.setText(this.ScreenTitle);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
                onBackPressed();
            }
        });
// Settings button click listener
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle settings button click
                // Open settings activity or show a settings menu
            }
        });

        pickUp = findViewById(R.id.toPick);
        dropTo = findViewById(R.id.toDrop);
        delivered = findViewById(R.id.Delivered);
        cancel = findViewById(R.id.cancel_order);

        OrderDetails = findViewById(R.id.track_order_details);

        order = OrderSession.getInstance().getOrder();
        Details = "For : "+order.getCustomerName()+"\n"+"Pick up : "+order.getPickUp()+"\nDrop by : "+order.getDropDown()+"\nRs. "+order.getFare()+" - "+order.getDistance()+" Kms";
        OrderDetails.setText(Details);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        String url = "https://www.google.com/maps/dir/?api=1&destination="+16.314679+ "," + 80.419150;

        webView.loadUrl(url);


        pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderSession.getInstance().getOrder().setStatus("Delivery Agent Started to pick up!");
                double pickLatitude = 37.7749; // Destination latitude
                double pickLongitude = -122.4194; // Destination longitude

                Navigate(pickLatitude,pickLongitude);

//                If pickUp location == agent Location disable the pickup button
                pickUp.setEnabled(false);
//                TODO : Verify the locations and send agent location to server periodically
            }
        });

        dropTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.setStatus("Order Picked Up!");

                pickUp.setEnabled(false);
//                There will be no cancelling once order is picked up
                cancel.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
                cancel.setEnabled(false);

                double dropLatitude = 37.7749; // Destination latitude
                double dropLongitude = -122.4194; // Destination longitude
                Navigate(dropLatitude,dropLongitude);

//                If dropTo location == agent Location disable the dropTo button
                dropTo.setEnabled(false);
//                TODO : Verify the locations and send agent location to server periodically
            }
        });

        delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.setStatus("Order Delivered!");

//                TODO : Order Closing process
//                If total delivery closing process completed
                delivered.setEnabled(false);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel.setEnabled(false);
//                TODO : Implement Cancellation Rules
            }
        });

    }

    public void Navigate(double destLat,double destLong){
        String uri = "google.navigation:q=" + destLat+ "," + destLong;
        Uri gmmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);



//        Start Map Intent
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            // Start the activity with the map intent
            startActivity(mapIntent);
        }
    }
}