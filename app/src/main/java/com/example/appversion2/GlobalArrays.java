package com.example.appversion2;

import java.util.ArrayList;

public class GlobalArrays
{
    //instance variables
    private static ArrayList<ServiceProfile> serviceArrayList = new ArrayList<ServiceProfile>();

    //private String serviceName;
    // private boolean firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, proofOfStatus, photoOfTheCustomer;


    //Getter/Setter methods
    public static ArrayList<ServiceProfile> getServiceArrayList() { return serviceArrayList; }

    public static void setServiceArrayList(ArrayList<ServiceProfile> newServiceArrayList) { serviceArrayList = newServiceArrayList; }

    //Service Functions
    public static void addServiceToArrayList(ServiceProfile newService) { serviceArrayList.add(newService); }

    public static void editServiceInArrayList(int index, ServiceProfile newService) { serviceArrayList.set(index, newService); }

    public static void removeServiceFromArrayList(int index) { serviceArrayList.remove(index); }

    public static int getArrayListLenth() { return serviceArrayList.size(); }

}
