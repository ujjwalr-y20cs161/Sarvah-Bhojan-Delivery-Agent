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

    public static  String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s");

        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String restOfWord = word.substring(1).toLowerCase();
                result.append(firstLetter).append(restOfWord).append(" ");
            }
        }

        return result.toString().trim();
    }
}




