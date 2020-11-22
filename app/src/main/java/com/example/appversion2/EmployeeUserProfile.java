package com.example.appversion2;

import java.util.ArrayList;

public class EmployeeUserProfile extends UserProfile {



    public static String validate(EmployeeUserProfile emp) {
        String temp;
        /* Validation code here (TASK 6)*/
        /* Please keep "success" as the return on a success,
        and return the reason for rejection on failure */
        temp = validateEmployeeNumber(emp.employeeNumber);
        if (!temp.equals("success")) {
            return temp;
        }
        return "success";
    }

    public static String validateEmployeeNumber(String employeeNumber) {

        /* The validation method above can call other methods that test each individual value */
        /* Each of these sub-methods can return a reason for failure,
        and the main validate method can just return whatever failure message it receives first,
        like I have done above */
        /* Also, please allow "-1" as a valid employee number, that is in use for a couple things. */
        return "success";
    }

    public static EmployeeUserProfile getEmployee(String email) {
        for (int i = 0; i < GlobalArrays.employees.size(); i++ ) {
            if (GlobalArrays.employees.get(i).getEmployeeEmail().equals(email)) {
                return GlobalArrays.employees.get(i);
            }
        }
        return null;
    }
    private String employeeNumber;
    private String address;
    private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    public void setPhoneNumber (String newPhoneNumber ) {
        phoneNumber = newPhoneNumber;
    }

    public EmployeeUserProfile(String employeeFirstName, String employeeLastName, String employeeEmail, String employeePassword, String employeeNumber) {
        super(employeeFirstName, employeeLastName, employeeEmail, employeePassword, "Employee");
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(String newEmployeeNumber) {
        employeeNumber = newEmployeeNumber;
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
