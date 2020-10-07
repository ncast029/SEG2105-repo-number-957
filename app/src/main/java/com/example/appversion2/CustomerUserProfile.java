package com.example.appversion2;

public class CustomerUserProfile extends Profile {

    public CustomerUserProfile(String customerFirstName, String customerLastName, String customerEmail, String customerPassword, String customerRole) {
        super(customerFirstName, customerLastName, customerEmail, customerPassword, customerRole);
    }

    public String getCustomerFirstName() {
        return super.getFirstName();
    }

    public String getCustomerLastName() {
        return super.getLastName();
    }

    public String getCustomerEmail() {
        return super.getEmail();
    }

    public String getCustomerPassword() {
        return super.getPassword();
    }

    public String getCustomerRole() {
        return super.getRole();
    }

}
