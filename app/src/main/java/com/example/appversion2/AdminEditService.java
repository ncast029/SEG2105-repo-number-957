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

public class AdminEditService extends AppCompatActivity {

    private Button confirmCreationOfService, backToAdminWelcome;
    private TextView pageInfoText, formInformation, documentInformation;
    private CheckBox firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomers;
    private EditText serviceName;
    private String serviceNameString;
    private FirebaseAuth firebaseAuth;

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
}
