package com.example.appversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminEditService extends ServiceProfile {

    private Button confirmEditOfService, backToAdminWelcome;
    private TextView pageInfoText, formInformation, documentInformation;
    private CheckBox firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomers;
    private EditText serviceName;
    private String serviceNameString;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_service_page);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        confirmEditOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(validate())
                {
                    //Find service and edit it.
                    ArrayList<ServiceProfile> serviceArrayList = getArrayList();

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
                    setArrayList(serviceArrayList);
                }
            }
        };

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
        pageInfoText = (TextView)findViewById(R.id.tvCreateServiceText);
        serviceName = (EditText)findViewById(R.id.etServiceName);
        formInformation = (TextView)findViewById(R.id.tvFormInformation);
        firstName = (CheckBox)findViewById(R.id.FirstNameButton);
        secondName = (CheckBox)findViewById(R.id.LastNameButton);
        dateOfBirth = (CheckBox)findViewById(R.id.DOBButton);
        address = (CheckBox)findViewById(R.id.addressButton);
        licenseType = (CheckBox)findViewById(R.id.LicenseTypeButton);

        documentInformation = (TextView)findViewById(R.id.tvDocumentInformation);
        proofOfResidence = (CheckBox)findViewById(R.id.ProofOfResidenceButton);
        ProofOfStatus = (CheckBox)findViewById(R.id.ProofOfStatusButton);
        photoOfTheCustomers = (CheckBox)findViewById(R.id.PhotoButton);

        confirmEditOfService = (Button)findViewById(R.id.confirmCreationButton);
        backToAdminWelcome = (Button)findViewById(R.id.backButton);
    }

    private Boolean validate() {
        Boolean result = false;
        Boolean singular = true;
        serviceNameString = serviceName.getText().toString();


        ArrayList<ServiceProfile> serviceArrayList = getArrayList();
        for(int i = 0; i < serviceArrayList.size(); i++)
        {
            if(serviceArrayList.get(i).getServiceName().equals(serviceNameString))
            {
                result = false;
                continue;
            }
        }

        if(serviceNameString.isEmpty()) {
            Toast.makeText(this, "Please make sure to name your service!", Toast.LENGTH_SHORT).show();
        }
        else if(!singular)
        {
            Toast.makeText(this, "Service name already in use. Please choose a new name!", Toast.LENGTH_SHORT).show();

        }
        else
        {
            result = true;
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

