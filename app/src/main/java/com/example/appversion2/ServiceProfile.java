package com.example.appversion2;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ServiceProfile extends AppCompatActivity {

    private String serviceName;
    private boolean firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, proofOfStatus, photoOfTheCustomer;

    //STATIC ARRAY LIST
    private static ArrayList<ServiceProfile> serviceArrayList = new ArrayList<ServiceProfile>();


    public ServiceProfile(String serviceName, boolean firstName, boolean secondName, boolean dateOfBirth, boolean address, boolean licenseType, boolean proofOfResidence, boolean ProofOfStatus, boolean photoOfTheCustomer)
    {
        this.serviceName = serviceName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.licenseType = licenseType;
        this.proofOfResidence = proofOfResidence;
        this.proofOfStatus = proofOfStatus;
        this.photoOfTheCustomer = photoOfTheCustomer;

        serviceArrayList.add(this);
    }

    public ArrayList<ServiceProfile> getArrayList()
    {
        return serviceArrayList;
    }

    public void setArrayList(ArrayList<ServiceProfile> newServiceArrayList)
    {
        serviceArrayList = newServiceArrayList;
    }

    public String getServiceName() { return serviceName; }

    public String getFirstName() {
        if(firstName) { return "First Name"; }
        else { return null; } }

    public String getSecondName() {
        if(firstName) { return "Second Name"; }
        else { return null; } }

    public String getDateOfBirth() {
        if(firstName) { return "Date of Birth"; }
        else { return null; } }

    public String getAddress() {
        if(firstName) { return "Address"; }
        else { return null; } }

    public String getLicenseType() {
        if(firstName) { return "License Type"; }
        else { return null; } }

    public String getProofOfresidence() {
        if(firstName) { return "Proof of Residence"; }
        else { return null; } }

    public String getProofOfStatus() {
        if(firstName) { return "Proof of Status"; }
        else { return null; } }

    public String getDateOfBirth() {
        if(firstName) { return "Photo of the Customer"; }
        else { return null; } }

    public boolean setFirstName(boolean state) {firstName = state;}
    public boolean setSecondName(boolean state) {return secondName = state;}
    public boolean setDateOfBirth(boolean state) {return dateOfBirth = state;}
    public boolean setAddress(boolean state) {return address = state;}
    public boolean setLicenseType(boolean state) {return licenseType = state;}
    public boolean setProofOfresidence(boolean state) {return proofOfResidence = state;}
    public boolean setProofOfStatus(boolean state) {return proofOfStatus = state;}
    public boolean setphotoOfTheCustomer(boolean state) {return photoOfTheCustomer = state;}

}//end serviceProfile
