package com.example.sarvah_bhojan_delivery_agent;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Agent implements Parcelable {
    public String firstName,lastName,gender;
    private String emailId,password;
    public int age;

    public Agent(String firstName, String lastName, String gender, String emailId, String password, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emailId = emailId;
        this.password = password;
        this.age = age;
    }

    public Agent(Agent agentIns) {
        this.firstName = agentIns.firstName;
        this.lastName = agentIns.lastName;
        this.age = agentIns.age;
        this.gender = agentIns.gender;
        this.emailId= agentIns.getEmailId();
        this.password = agentIns.getPassword();
    }

    protected Agent(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        gender = in.readString();
        emailId = in.readString();
        password = in.readString();
        age = in.readInt();
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
