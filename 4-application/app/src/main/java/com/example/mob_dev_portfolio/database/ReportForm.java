package com.example.mob_dev_portfolio.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ReportForm {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    @ColumnInfo(name = "owner_name")
    private String ownerName;

    @ColumnInfo(name = "your_name")
    private String yourName;

    @ColumnInfo(name = "call_type")
    private String callType;

    @ColumnInfo(name = "comment")
    private String comment;

    @Override
    public String toString() {
        return "ReportForm{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                ", ownerName='" + ownerName + '\'' +
                ", yourName='" + yourName + '\'' +
                ", callType='" + callType + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ReportForm (String phoneNumber, String ownerName, String yourName, String callType, String comment) {
        this.phoneNumber = phoneNumber;
        this.ownerName = ownerName;
        this.yourName = yourName;
        this.callType = callType;
        this.comment = comment;

    }
}
