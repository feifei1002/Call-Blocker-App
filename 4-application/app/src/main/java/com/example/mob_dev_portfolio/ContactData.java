package com.example.mob_dev_portfolio;

public class ContactData {
    private String contactName;
    private String phoneNumber;

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

    @Override
    public String toString() {
        return  "Contact Name='" + contactName + '\'' +
                ", Phone Number='" + phoneNumber + '\'' ;
    }
}
