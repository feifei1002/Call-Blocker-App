package com.example.mob_dev_portfolio.databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "blocklist")
public class BlockList {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "phone_no")
    private String phoneNo;

    public BlockList(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "BlockList{" +
                "id=" + id +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
