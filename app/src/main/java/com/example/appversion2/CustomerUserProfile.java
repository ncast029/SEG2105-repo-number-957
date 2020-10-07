package com.example.appversion2;

public class CustomerUserProfile {
    public String CustomerFirstName;
    public String CustomerLastName;
    public String CustomerEmail;
    public String CustomerPassword;
    public String CustomerRole;

    public CustomerUserProfile(String customerFirstName, String customerLastName, String customerEmail, String customerPassword, String customerRole) {
        CustomerFirstName = customerFirstName;
        CustomerLastName = customerLastName;
        CustomerEmail = customerEmail;
        CustomerPassword = customerPassword;
        CustomerRole = customerRole;
    }
}
