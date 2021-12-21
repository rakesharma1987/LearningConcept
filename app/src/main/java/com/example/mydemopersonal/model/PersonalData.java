package com.example.mydemopersonal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PersonalData implements Serializable {
    @SerializedName("fName")
    private String fName;

    @SerializedName("sName")
    private String sName;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("gender")
    private String gender;

    private String key;

    public PersonalData() {
    }

    public PersonalData(String fName, String sName, String email, String phone) {
        this.fName = fName;
        this.sName = sName;
        this.email = email;
        this.phone = phone;
    }

    public PersonalData(String fName, String sName, String email, String phone, String gender) {
        this.fName = fName;
        this.sName = sName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getfName() {
        return fName;
    }

    public String getsName() {
        return sName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
