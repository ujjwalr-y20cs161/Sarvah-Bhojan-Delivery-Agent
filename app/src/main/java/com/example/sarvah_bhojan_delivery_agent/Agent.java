package com.example.sarvah_bhojan_delivery_agent;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Agent  {
    public String firstName,lastName,gender;
    private String emailId,password;
    private String uid;
    public int age;
    private static long uidGen = 1000;

    public Agent(String firstName , String lastName, String gender, String emailId, String password, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emailId = emailId;
        this.password = password;
        this.age = age;
        this.uid = generateUID();
    }

    public String getUid() {
        return uid;
    }

    public Agent(String emailId, String password) {
        this.firstName="FirstName";
        this.lastName="LastName";
        this.age=0;
        this.gender ="default";
        this.emailId = emailId;
        this.password = password;
        this.uid = generateUID();
    }

    public Agent(String firstName, String emailId, String password) {
        this.firstName = firstName;
        this.emailId = emailId;
        this.password = password;
        this.uid = generateUID();
    }

    public Agent(Agent agentIns) {
        this.firstName = agentIns.firstName;
        this.lastName = agentIns.lastName;
        this.age = agentIns.age;
        this.gender = agentIns.gender;
        this.emailId= agentIns.getEmailId();
        this.password = agentIns.getPassword();
        this.uid = agentIns.getUid();
    }

    public Agent(){

    }


    private static String generateUID(){
        uidGen = uidGen+1;
        return Long.toString(uidGen);
    }

    public void setUid(String FireBaseID){
        this.uid = FireBaseID;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
