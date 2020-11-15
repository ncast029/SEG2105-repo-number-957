package com.example.appversion2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdminEditService extends AppCompatActivity {

    private Button confirmEditOfService, backToAdminWelcome;
    private CheckBox firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomers;
    private EditText serviceName;
    private String serviceNameString;
    Context temp;

    /*
    public AdminEditService(String serviceName, boolean firstName, boolean secondName, boolean dateOfBirth, boolean address, boolean licenseType, boolean proofOfResidence, boolean ProofOfStatus, boolean photoOfTheCustomer)
    {
        super(serviceName, firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomer);
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_service_page);
        setupUIViews();
        temp = this;

        confirmEditOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               if(validate())
                {
                    //Find service and edit it.
                    ArrayList<ServiceProfile> serviceArrayList = ServiceProfile.getArrayList();

                    for(int i = 0; i < serviceArrayList.size(); i++)
                    {
                        if(serviceArrayList.get(i).getServiceName().equals(serviceNameString))
                        {
                            serviceArrayList.get(i).setFirstName(firstName.isChecked());
                            serviceArrayList.get(i).setSecondName(secondName.isChecked());
                            serviceArrayList.get(i).setDateOfBirth(dateOfBirth.isChecked());
                            serviceArrayList.get(i).setAddress(address.isChecked());
                            serviceArrayList.get(i).setLicenseType(licenseType.isChecked());
                            serviceArrayList.get(i).setProofOfresidence(proofOfResidence.isChecked());
                            serviceArrayList.get(i).setProofOfStatus(ProofOfStatus.isChecked());
                            serviceArrayList.get(i).setphotoOfTheCustomer(photoOfTheCustomers.isChecked());
                        }
                    }
                    Toast.makeText(temp, "Service change successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AdminEditService.this, AdminWelcomePage.class));
                    finish();
                }
            }
        });

        backToAdminWelcome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AdminEditService.this, AdminWelcomePage.class));
            }
        });
    }



    private void setupUIViews() {
        serviceName = (EditText)findViewById(R.id.etServiceName);
        firstName = (CheckBox)findViewById(R.id.FirstNameButton);
        secondName = (CheckBox)findViewById(R.id.LastNameButton);
        dateOfBirth = (CheckBox)findViewById(R.id.DOBButton);
        address = (CheckBox)findViewById(R.id.addressButton);
        licenseType = (CheckBox)findViewById(R.id.LicenseTypeButton);
        proofOfResidence = (CheckBox)findViewById(R.id.ProofOfResidenceButton);
        ProofOfStatus = (CheckBox)findViewById(R.id.ProofOfStatusButton);
        photoOfTheCustomers = (CheckBox)findViewById(R.id.PhotoButton);

        confirmEditOfService = (Button)findViewById(R.id.ConfirmEditServiceButton);
        backToAdminWelcome = (Button)findViewById(R.id.backToAdminButton);
    }


    private Boolean validate() {
        Boolean result = false;
        serviceNameString = serviceName.getText().toString();


        ArrayList<ServiceProfile> serviceArrayList = ServiceProfile.getArrayList();
        for(int i = 0; i < serviceArrayList.size(); i++)
        {
            if(serviceArrayList.get(i).getServiceName().equals(serviceNameString))
            {
                result = true;
            }
        }

        if(serviceNameString.isEmpty()) {
            Toast.makeText(temp, "Please provide a service name to edit", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    /*
    private void editExistingService()
    {

    }
*/
/*
    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        ServiceProfile newService = new ServiceProfile(serviceNameString, firstName.isChecked(), secondName.isChecked(), dateOfBirth.isChecked(),
                address.isChecked(), licenseType.isChecked(), proofOfResidence.isChecked(), ProofOfStatus.isChecked(),
                photoOfTheCustomers.isChecked());
        myRef.setValue(newService);
    }
 */

}

