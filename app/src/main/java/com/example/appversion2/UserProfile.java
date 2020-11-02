package com.example.appversion2;

public class UserProfile {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public String getUserFirstName() {
        return firstName;
    }

    public String getUserLastName() {
        return lastName;
    }

    public String getUserEmail() {
        return email;
    }

    public String getUserPassword() {
        return password;
    }

    public String getUserRole() {
        return role;
    }

    public void setUserFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUserLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }

    public void setUserRole(String role) {
        this.role = role;
    }

    public UserProfile(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
