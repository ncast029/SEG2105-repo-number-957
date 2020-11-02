package com.example.appversion2;

public class FormSubmission {

    private String firstName;
    private String lastName;
    private String dob;
    private String addr;
    private String formType;

    public FormSubmission(String firstName, String lastName, String dateOfBirth, String address, String formType) {
        this.firstName = firstName;
        this.lastName = lastName;
        dob = dateOfBirth;
        addr = address;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getDob() {
        return dob;
    }
    public String getAddr() {
        return addr;
    }
    public String getFormType() {
        return formType;
    }

    public void setFirstName(String n) {
        firstName = n;
    }
    public void setLastName(String n) {
        lastName = n;
    }
    public void setDob(String d) {
        dob = d;
    }
    public void setAddr(String a) {
        addr = a;
    }
    public void setFormType(String ft) {
        formType = ft;
    }
}
