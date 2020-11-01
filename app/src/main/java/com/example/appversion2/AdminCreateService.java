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

public class AdminCreateService extends AppCompatActivity {

    private Button confirmCreationOfService, backToAdminWelcome;
    private TextView pageInfoText, formInformation, documentInformation, stateInfoText;
    private CheckBox firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomers, stateOfService;
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

        confirmCreationOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    //Upload service name and requirements to the database
                    firebaseAuth.createService(serviceNameString, firstName.isChecked(), secondName.isChecked(), dateOfBirth.isChecked(),
                                                address.isChecked(), licenseType.isChecked(), proofOfResidence.isChecked(), ProofOfStatus.isChecked(),
                                                photoOfTheCustomers.isChecked()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {

                            if(task.isSuccessful())
                            {
                                sendUserData();
                                Toast.makeText(CustomerRegistration.this, "Registration of Service was Successful", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(CustomerRegistration.this, MainActivity.class));
                            } else
                                {
                                Toast.makeText(CustomerRegistration.this, "Registration of Service Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        };

        backToAdminWelcome.setOnClickListener(new View.OnClickListener()
        {

        }
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
        serviceNameString = serviceName.getText().toString();

        if(serviceNameString.isEmpty()) {
            Toast.makeText(this, "Please make sure to name your service!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;
    }


    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        ServiceProfile newService = new ServiceProfile(serviceNameString, firstName.isChecked(), secondName.isChecked(), dateOfBirth.isChecked(),
                                                        address.isChecked(), licenseType.isChecked(), proofOfResidence.isChecked(), ProofOfStatus.isChecked(),
                                                        photoOfTheCustomers.isChecked());
        myRef.setValue(newService);
    }
}