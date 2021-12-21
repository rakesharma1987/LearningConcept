package com.example.mydemopersonal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonalParcebleData implements Parcelable {
    String fName;
    String lName;
    String email;
    String phone;
//    String gender;

    public PersonalParcebleData(String fName, String lName, String email, String phone/*, String gender*/) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
//        this.gender = gender;
    }

    protected PersonalParcebleData(Parcel in) {
        fName = in.readString();
        lName = in.readString();
        email = in.readString();
        phone = in.readString();
//        gender = in.readString();
    }

    public static final Creator<PersonalParcebleData> CREATOR = new Creator<PersonalParcebleData>() {
        @Override
        public PersonalParcebleData createFromParcel(Parcel in) {
            return new PersonalParcebleData(in);
        }

        @Override
        public PersonalParcebleData[] newArray(int size) {
            return new PersonalParcebleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fName);
        parcel.writeString(lName);
        parcel.writeString(phone);
        parcel.writeString(email);
//        parcel.writeString(gender);
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
