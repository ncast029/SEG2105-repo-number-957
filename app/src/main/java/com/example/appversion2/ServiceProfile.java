package com.example.appversion2;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ServiceProfile {

    private String serviceName;
    private boolean firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, proofOfStatus, photoOfTheCustomer;

    //STATIC ARRAY LIST
    private static ArrayList<ServiceProfile> serviceArrayList = new ArrayList<ServiceProfile>();


    public ServiceProfile(String serviceName, boolean firstName, boolean secondName, boolean dateOfBirth, boolean address, boolean licenseType, boolean proofOfResidence, boolean proofOfStatus, boolean photoOfTheCustomer)
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
    }

    public static ArrayList<ServiceProfile> getArrayList()
    {
        return serviceArrayList;
    }

    public static void setArrayList(ArrayList<ServiceProfile> newServiceArrayList)
    {
        serviceArrayList = newServiceArrayList;
    }

    public static void addServiceToArrayList(ServiceProfile newService)
    {
        serviceArrayList.add(newService);
    }


    public String getServiceName() { return serviceName; }

    public String getFirstName() {
        if(firstName) { return "First Name"; }
        else { return null; } }

    public String getSecondName() {
        if(secondName) { return "Second Name"; }
        else { return null; } }

    public String getDateOfBirth() {
        if(dateOfBirth) { return "Date of Birth"; }
        else { return null; } }

    public String getAddress() {
        if(address) { return "Address"; }
        else { return null; } }

    public String getLicenseType() {
        if(licenseType) { return "License Type"; }
        else { return null; } }

    public String getProofOfResidence() {
        if(proofOfResidence) { return "Proof of Residence"; }
        else { return null; } }

    public String getProofOfStatus() {
        if(proofOfStatus) { return "Proof of Status"; }
        else { return null; } }

    public String getPhotoOfTheCustomer() {
        if (photoOfTheCustomer) { return "Photo of the Customer"; }
        else { return null; } }



    public void setFirstName(boolean state) {firstName = state;}
    public void setSecondName(boolean state) {secondName = state;}
    public void setDateOfBirth(boolean state) {dateOfBirth = state;}
    public void setAddress(boolean state) {address = state;}
    public void setLicenseType(boolean state) {licenseType = state;}
    public void setProofOfresidence(boolean state) {proofOfResidence = state;}
    public void setProofOfStatus(boolean state) {proofOfStatus = state;}
    public void setphotoOfTheCustomer(boolean state) {photoOfTheCustomer = state;}

}//end serviceProfile



/*
package com.example.appversion2;

import java.util.ArrayList;

public class ServiceArray {

    private ArrayList<ServiceProfile> serviceArrayList = new ArrayList<ServiceProfile>(); //stores all services

    public ServiceArray(ServiceProfile newService)
    {
        serviceArrayList.add(newService);
    }

    public ArrayList<ServiceProfile> getArrayOfServices() {
        return serviceArrayList;
    }

    public void setArrayOfServices(ArrayList<ServiceProfile> newList) {
        serviceArrayList = newList;
    }

    public void editService(int index, ServiceProfile newServiceProfile)
    {
        serviceArrayList.set(index, newServiceProfile);
    }

    public void removeService(int index)
    {
        serviceArrayList.remove(index);
    }

}

 */
