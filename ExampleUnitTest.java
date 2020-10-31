package com.example.appversion2;

public class ExampleUnitTest
{
    //@Test

    //Creating two employees
    public void testEmployeeUserProfile()
    {
        EmployeeUserProfile testEmployee = new EmployeeUserProfile("Kevin", "Baker", "bkevin123@gmail.com", "BakerCaker", "0972", "employee");
        EmployeeUserProfile testEmployee2 = new EmployeeUserProfile("Marcus", "Baker", "bMarcus123@gmail.com", "MarcusTheBaker", "0973", "employee");
    }//testEmployeeUserProfile

    //Creating a customer
    public void testCustomerUserProfile()
    {
        CustomerUserProfile testCustomer = new CustomerUserProfile("Clive", "James", "jClive123@gmail.com", "CliveAlive", "customer");
    }//testEmployeeUserProfile

}//end ExmapleUnitTest
