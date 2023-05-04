package com.example.mob_dev_portfolio.classes;

public class ContactData {
    private String contactName;
    private String phoneNumber;
    private String callDate;
    private String callType;
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    @Override
    public String toString() {
        return  "Contact Name:'" + contactName + '\'' +
                ", Phone Number:'" + phoneNumber + '\'' +
                ", Call Date: '" + callDate + '\'' +
                ", Call Type: '" + callType + '\'';
    }
}
