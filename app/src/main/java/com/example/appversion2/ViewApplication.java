package com.example.appversion2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class ViewApplication extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application);
        String type = getIntent().getStringExtra("type");
        String name = getIntent().getStringExtra("name");
        FormSubmission form = null;

        for (int i = 0; i < GlobalArrays.forms.size(); i++) {
            if ((GlobalArrays.forms.get(i).getFirstName()+" "+GlobalArrays.forms.get(i).getLastName()).equals(name) && GlobalArrays.forms.get(i).getFormType().equals(type)) {
                form = GlobalArrays.forms.get(i);
                break;
            }
        }

        if (!type.equals("Driver's License")) {
            ((TextView) findViewById(R.id.lTypeBox)).setVisibility(View.INVISIBLE);
        } else {
            ((TextView) findViewById(R.id.lTypeBox)).setText(((DriversForm) form).getgLevel());
        }

        ((TextView) findViewById(R.id.formName)).setText(type + " Form");
        ((TextView) findViewById(R.id.fNameBox)).setText(form.getFirstName());
        ((TextView) findViewById(R.id.lNameBox)).setText(form.getLastName());
        ((TextView) findViewById(R.id.dobBox)).setText(form.getDob());
        ((TextView) findViewById(R.id.addrBox)).setText(form.getAddr());

        Button approveButton = (Button) findViewById(R.id.approveFormBtn);
        Button rejectButton = (Button) findViewById(R.id.rejectFormButton);
        Button backButton = (Button) findViewById(R.id.processLaterBtn);

        final FormSubmission finalForm = form;
        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<GlobalArrays.forms.size(); i++) {
                    if (GlobalArrays.forms.get(i) == finalForm) {
                        GlobalArrays.forms.remove(i);
                        break;
                    }
                }
                Toast.makeText(ViewApplication.this,"Application approved!", Toast.LENGTH_SHORT).show();
                ProcessApplications.thisActivity.finish();
                finish();
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    for (int i = 0; i<GlobalArrays.forms.size(); i++) {
                        if (GlobalArrays.forms.get(i) == finalForm) {
                            GlobalArrays.forms.remove(i);
                            break;
                        }
                    }
                    Toast.makeText(ViewApplication.this,"Application rejected!", Toast.LENGTH_SHORT).show();
                ProcessApplications.thisActivity.finish();
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
