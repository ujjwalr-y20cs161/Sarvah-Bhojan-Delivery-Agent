package com.example.sarvah_bhojan_delivery_agent;

import android.app.Application;

public class MyApp extends Application {
    private Agent myAgent;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize your object here
    }

    public void setMyAgent(Agent agentInstance){
        this.myAgent = new Agent(agentInstance);
    }

    public Agent getMyAgent() {
        return myAgent;
    }
}




