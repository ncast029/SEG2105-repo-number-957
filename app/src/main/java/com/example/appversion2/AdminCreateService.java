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

public class AdminCreateService extends ServiceProfile {

    private Button confirmCreationOfService, backToAdminWelcome;
    private TextView pageInfoText, formInformation, documentInformation, stateInfoText;
    private CheckBox firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomers, stateOfService;
    private EditText serviceName;
    private String serviceNameString;
    private FirebaseAuth firebaseAuth;


    public AdminCreateService(String serviceName, boolean firstName, boolean secondName, boolean dateOfBirth, boolean address, boolean licenseType, boolean proofOfResidence, boolean ProofOfStatus, boolean photoOfTheCustomer)
    {
        super(serviceName, firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_service_page);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        confirmCreationOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    //this line will call the SericeProfile class and create a new service with the paramters as requested by the admin
                    ServiceProfile addNewService = new ServiceProfile(serviceNameString, firstName.isChecked(), secondName.isChecked(),
                            dateOfBirth.isChecked(), address.isChecked(), licenseType.isChecked(), proofOfResidence.isChecked(),
                            ProofOfStatus.isChecked(), photoOfTheCustomers.isChecked());
                }
            }
        };

        backToAdminWelcome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AdminCreateService.this, AdminWelcomePage.class));
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

        stateInfoText = (TextView)findViewById(R.id.tvServiceState);
        stateOfService = (CheckBox)findViewById(R.id.toggleServiceButton);

        confirmCreationOfService = (Button)findViewById(R.id.confirmCreationButton);
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
}
