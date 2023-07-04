package com.example.sarvah_bhojan_delivery_agent;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Agent  {
    public String firstName,lastName,gender;
    private String emailId,password;
    private String uid;

    private String paymentAccNo;
    public int age;
    private static long uidGen = 1000;

    private Date joined;

    public String getPaymentAccNo() {
        return paymentAccNo;
    }

    public void setPaymentAccNo(String paymentAccNo) {
        this.paymentAccNo = paymentAccNo;
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
        this.joined = new Date();
    }

    public Agent(String firstName, String emailId, String password) {
        this.firstName = firstName;
        this.emailId = emailId;
        this.password = password;
        this.uid = generateUID();
        this.joined = new Date();
    }

    public Agent(Agent agentIns) {
        this.firstName = agentIns.firstName;
        this.lastName = agentIns.lastName;
        this.age = agentIns.age;
        this.gender = agentIns.gender;
        this.emailId= agentIns.getEmailId();
        this.password = agentIns.getPassword();
        this.uid = agentIns.getUid();
        this.joined = agentIns.getJoined();
    }

    public Agent(){

    }

    public Agent(String firstName , String lastName, String gender, String emailId, String password, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emailId = emailId;
        this.password = password;
        this.age = age;
        this.uid = generateUID();
        this.joined = new Date();
    }
    public Date getJoined() {
        return joined;
    }
    public void setJoined(Date joined) {
        this.joined = joined;
    }
    private static String generateUID(){
        uidGen = uidGen+1;
        return Long.toString(uidGen);
    }
    public void setUid(String FireBaseID){this.uid = FireBaseID;}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAge() {
        return Integer.toString(age);
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
