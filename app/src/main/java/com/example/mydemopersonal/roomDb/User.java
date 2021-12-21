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

    public User(String fName, String lName, String email, String phoneNo) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNo = phoneNo;
    }
}
