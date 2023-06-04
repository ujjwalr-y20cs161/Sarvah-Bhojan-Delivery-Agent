package com.example.sarvah_bhojan_delivery_agent;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Agent implements Parcelable {
    public String firstName,lastName,gender;
    private String emailId,password;
    private final long uid;
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

    public long getUid() {
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

    protected Agent(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        gender = in.readString();
        emailId = in.readString();
        password = in.readString();
        age = in.readInt();
       uid = in.readLong();
    }

    private static long generateUID(){
        uidGen = uidGen+1;
        return uidGen;
    }

    public static final Creator<Agent> CREATOR = new Creator<Agent>() {
        @Override
        public Agent createFromParcel(Parcel in) {
            return new Agent(in);
        }

        @Override
        public Agent[] newArray(int size) {
            return new Agent[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(gender);
        parcel.writeString(emailId);
        parcel.writeString(password);
        parcel.writeInt(age);
    }
}
