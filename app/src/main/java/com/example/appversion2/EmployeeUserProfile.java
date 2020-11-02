package com.example.appversion2;

public class EmployeeUserProfile extends UserProfile {
    private String employeeNumber;

    public EmployeeUserProfile(String employeeFirstName, String employeeLastName, String employeeEmail, String employeePassword, String employeeNumber, String employeeRole) {
        super(employeeFirstName, employeeLastName, employeeEmail, employeePassword, employeeRole);
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmployeeFirstName() {
        return super.getUserFirstName();
    }

    public String getEmployeeLastName() {
        return super.getUserLastName();
    }

    public String getEmployeeEmail() {
        return super.getUserEmail();
    }

    public String getEmployeePassword() {
        return super.getUserPassword();
    }

    public String getEmployeeRole() {
        return super.getUserRole();
    }

}
