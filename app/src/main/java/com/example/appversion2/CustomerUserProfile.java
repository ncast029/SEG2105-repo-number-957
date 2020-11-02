package com.example.appversion2;

public class CustomerUserProfile extends UserProfile {

    public CustomerUserProfile(String customerFirstName, String customerLastName, String customerEmail, String customerPassword, String customerRole) {
        super(customerFirstName, customerLastName, customerEmail, customerPassword, customerRole);
    }

    public String getCustomerFirstName() {
        return super.getUserFirstName();
    }

    public String getCustomerLastName() {
        return super.getUserLastName();
    }

    public String getCustomerEmail() {
        return super.getUserEmail();
    }

    public String getCustomerPassword() {
        return super.getUserPassword();
    }

    public String getCustomerRole() {
        return super.getUserRole();
    }

}
