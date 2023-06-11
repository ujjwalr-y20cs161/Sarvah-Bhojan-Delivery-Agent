package com.example.sarvah_bhojan_delivery_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.color.DynamicColors;

import org.w3c.dom.Text;

public class Tracker extends AppCompatActivity {

    private String ScreenTitle = "Order Tracker";
    private TextView OrderDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);


        DynamicColors.applyToActivityIfAvailable(this);
        ActionBar actionBar = getSupportActionBar();

        // Hide the action bar title and show only the app icon
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(this.ScreenTitle);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

        String Details="";
        OrderDetails = findViewById(R.id.track_order_details);

        Order order = OrderSession.getInstance().getOrder();
        Details = order.getId()+"\n"+order.getCustomerName()+"\n"+order.getPickUp()+"-"+order.getDropDown()+"\n"+order.getStatus();
        OrderDetails.setText(Details);
    }
}