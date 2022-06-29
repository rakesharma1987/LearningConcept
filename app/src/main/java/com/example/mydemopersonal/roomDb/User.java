package com.example.mydemopersonal.roomDb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uId;

    @ColumnInfo(name = "fName")
    public String fName;

    @ColumnInfo(name = "mName")
    public String mName;

    @ColumnInfo(name = "lName")
    public String lName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "phoneNo")
    public String phoneNo;

    public User() {
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public User(String fName, String lName, String email, String phoneNo, String mName) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.mName = mName;
    }
}
