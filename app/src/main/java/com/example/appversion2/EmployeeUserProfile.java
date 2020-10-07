package com.example.appversion2;

public class EmployeeUserProfile extends Profile {
    private String employeeNumber;

    public EmployeeUserProfile(String employeeFirstName, String employeeLastName, String employeeEmail, String employeePassword, String employeeNumber, String employeeRole) {
        super(employeeFirstName, employeeLastName, employeeEmail, employeePassword, employeeRole);
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmployeeFirstName() {
        return super.getFirstName();
    }

    public String getEmployeeLastName() {
        return super.getLastName();
    }

    public String getEmployeeEmail() {
        return super.getEmail();
    }

    public String getEmployeePassword() {
        return super.getPassword();
    }

    public String getEmployeeRole() {
        return super.getRole();
    }

}
