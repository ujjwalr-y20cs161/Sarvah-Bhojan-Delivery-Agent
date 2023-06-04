package com.example.sarvah_bhojan_delivery_agent;

import android.app.Application;

public class MyApp extends Application {
    private static Agent myAgent;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize your object here
    }

    public static void setMyAgent(Agent agentInstance){
        myAgent = new Agent(myAgent);
    }

    public static Agent getMyAgent() {
        return myAgent;
    }
}




