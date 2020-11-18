package com.example.appversion2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdminEditService extends AppCompatActivity {

    private Button confirmEditOfService, backToAdminWelcome;
    private CheckBox firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomers;
    private EditText serviceName;
    private String serviceNameString;
    Context temp;

    private static ArrayList<ServiceProfile> serviceArrayListForEdit = new ArrayList<ServiceProfile>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_service_page);
        setupUIViews();
        temp = this;

        serviceArrayListForEdit = GlobalArrays.getServiceArrayList();

        confirmEditOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               if(validate())
                {
                    ServiceProfile service = new ServiceProfile(serviceNameString, firstName.isChecked(), secondName.isChecked(),
                            dateOfBirth.isChecked(), address.isChecked(), licenseType.isChecked(), proofOfResidence.isChecked(),
                            ProofOfStatus.isChecked(), photoOfTheCustomers.isChecked());

                    for(int i = 0; i < serviceArrayListForEdit.size(); i++)
                    {
                        if(serviceArrayListForEdit.get(i).getServiceName().equals(serviceNameString))
                        {
                            GlobalArrays.editServiceInArrayList(i, service);
                            break;
                        }
                    }
                    Toast.makeText(temp, "Sucessfully editted " + serviceNameString + " service.", Toast.LENGTH_SHORT).show();
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
        ProofOfStatus = (CheckBox)findViewById(R.id.ProofofStatusButton);
        photoOfTheCustomers = (CheckBox)findViewById(R.id.PhotoButton);

        confirmEditOfService = (Button)findViewById(R.id.ConfirmEditServiceButton);
        backToAdminWelcome = (Button)findViewById(R.id.backToAdminButton);
    }


    private Boolean validate() {
        serviceNameString = serviceName.getText().toString();

        if(serviceNameString.isEmpty()) {
            Toast.makeText(temp, "Please provide a service name to edit", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            for(int i = 0; i < serviceArrayListForEdit.size(); i++)
            {
                if(serviceArrayListForEdit.get(i).getServiceName().equals(serviceNameString))
                {
                    return true;
                }
            }
            Toast.makeText(temp, "Please provide a valid service name to edit", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

