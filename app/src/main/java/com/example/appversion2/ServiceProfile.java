package com.example.appversion2;

public class ServiceProfile{

    private String serviceName;
    private boolean firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, proofOfStatus, photoOfTheCustomer;



    public ServiceProfile(String serviceName, boolean firstName, boolean secondName, boolean dateOfBirth, boolean address, boolean licenseType, boolean proofOfResidence, boolean ProofOfStatus, boolean photoOfTheCustomer)
    {
        this.serviceName = serviceName;
        this. firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.licenseType = licenseType;
        this.proofOfResidence = proofOfResidence;
        this.proofOfStatus = proofOfStatus;
        this.photoOfTheCustomer = photoOfTheCustomer;
    }

    public String getServiceName() {return serviceName;}
    public boolean getFirstName() {return firstName;}
    public boolean getSecondName() {return secondName;}
    public boolean getDateOfBirth() {return dateOfBirth;}
    public boolean getAddress() {return address;}
    public boolean getLicenseType() {return licenseType;}
    public boolean getProofOfresidence() {return proofOfResidence;}
    public boolean getProofOfStatus() {return proofOfStatus;}
    public boolean getphotoOfTheCustomer() {return photoOfTheCustomer;}

    public boolean setFirstName(boolean state) {firstName = state;}
    public boolean setSecondName(boolean state) {return secondName = state;}
    public boolean setDateOfBirth(boolean state) {return dateOfBirth = state;}
    public boolean setAddress(boolean state) {return address = state;}
    public boolean setLicenseType(boolean state) {return licenseType = state;}
    public boolean setProofOfresidence(boolean state) {return proofOfResidence = state;}
    public boolean setProofOfStatus(boolean state) {return proofOfStatus = state;}
    public boolean setphotoOfTheCustomer(boolean state) {return photoOfTheCustomer = state;}

}//end serviceProfile
