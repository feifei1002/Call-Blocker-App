package com.example.mob_dev_portfolio;

public class PhoneNoAPI {
    private String phoneNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public PhoneNoAPI(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "PhoneNoAPI{" +
                "phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
