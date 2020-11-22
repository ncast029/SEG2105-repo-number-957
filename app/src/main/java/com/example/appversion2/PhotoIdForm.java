package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;

public class PhotoIdForm extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private String rate;
    private ArrayList<File> files;
    public String getRate() {
        return rate;
    }
    public void setRate(String newRate) {
        rate = newRate;
    }
    String docs;

    protected void onCreate(Bundle savedInstanceState) {
        rate = "120.00";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_id_registration);

        Button helpButton = (Button) findViewById(R.id.hlpbtn);

        final ServiceProfile details = ServiceProfile.getArrayList().get(2);
        /*if (details.getFirstName() != null) {

        }
        if (details.getSecondName() != null) {

        }*/
        if ( details.getDateOfBirth() == null ) {
            ((EditText)findViewById(R.id.dobField)).setVisibility(View.INVISIBLE);
        }
        if ( details.getAddress() == null ) {
            ((EditText)findViewById(R.id.addrField)).setVisibility(View.INVISIBLE);
        }
        if ( details.getLicenseType() == null ) {
            ((RadioGroup)findViewById(R.id.radioGroup)).setVisibility(View.INVISIBLE);
        }
        String res = "Please upload a proof of residence.";
        String stat = "Please upload a proof of status.";
        String photo = "Please upload a photo of yourself.";
        docs = "";

        if ( details.getProofOfResidence() != null ) {
            docs += res;
        }
        if ( details.getProofOfStatus() != null ) {
            docs += " " + stat;
        }
        if ( details.getPhotoOfTheCustomer() != null) {
            docs += " " + photo;
        }

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);
                ((TextView) popupView.findViewById(R.id.doctextbox)).setText(docs);
                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window token
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });

            }
        });

        /*
        Button photoButton = (Button) findViewById(R.id.uibtn);
        photoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
                AlertDialog.Builder builder = new AlertDialog.Builder(DriversLicense.this);
                builder.setTitle("Upload Photo");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo"))
                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File("", "temp.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            startActivityForResult(intent, 1);
                        }
                        else if (options[item].equals("Choose from Gallery"))
                        {
                            Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, 2);
                        }
                        else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
                        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

                    }
                });
                builder.show();
            }
        });
        */




        /*
        Button fileButton = (Button) findViewById(R.id.filebtn);
        fileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {

            }
        });
        */

        Button submitButton = (Button) findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PhotoIdForm.this);
                builder.setCancelable(true);
                builder.setTitle("Confirm Photo ID Form Submission");
                builder.setMessage("Once your application is processed $" + rate + " will be charged to your account.");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String dob = ((EditText)findViewById(R.id.dobField)).getText().toString();
                                String addr = ((EditText)findViewById(R.id.addrField)).getText().toString();
                                if ((!dobValidate(dob) && details.getDateOfBirth() != null) || (!addrValidate(addr) && details.getAddress() != null)) {
                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(PhotoIdForm.this);
                                    builder2.setCancelable(false);
                                    builder2.setTitle("ERROR");
                                    builder2.setMessage("Your input was not valid. Please try again, or go back to a different page.");
                                    builder2.setPositiveButton("I Understand",
                                            new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) {}});
                                    AlertDialog dialog2 = builder2.create();
                                    dialog2.show();
                                } else {
                                    String success = "";
                                    if (details.getDateOfBirth() != null && details.getAddress() != null) {
                                        success = submitForm(dob, addr);
                                    } else if (details.getDateOfBirth() != null) {
                                        success = submitForm(dob, "");
                                    } else if (details.getAddress() != null) {
                                        success = submitForm("", addr);
                                    } else {
                                        success = submitForm("", "");
                                    }
                                    //String success = submitForm(dob, addr);
                                    if (!success.equals("")) {
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(PhotoIdForm.this);
                                        builder2.setCancelable(false);
                                        builder2.setTitle("ERROR");
                                        //builder2.setMessage(success);
                                        builder2.setMessage("An error has occurred while processing your request. Please try again later.");
                                        builder2.setPositiveButton("Ok",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        finish();
                                                    }
                                                });


                                        AlertDialog dialog2 = builder2.create();
                                        dialog2.show();
                                    } else {
                                        finish();
                                    }
                                }}
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    private String submitForm(String dob, String address) {
        try {/*
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
            myRef.setValue(form);*/
            FormSubmission form = new FormSubmission( /*getIntent().getExtras().getString("FirstName")*/ "", /*getIntent().getExtras().getString("LastName")*/ "", dob, address, "Photo Id");

            GlobalArrays.forms.add(form);
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private boolean dobValidate(String dob) {
        if (dob == null || dob.equals("")) return false;
        if ( dob.length() != 8 ) return false;
        String doB[] = dob.split("/");
        if (doB.length != 3) return false;
        for (int i = 0; i < 3; i++) {
            if (doB[i].length() != 2) {
                return false;
            }
        }
        if (Integer.parseInt(doB[0]) > 31 || Integer.parseInt(doB[0]) < 1) return false;
        if (Integer.parseInt(doB[1]) > 12 || Integer.parseInt(doB[1]) < 1) return false;
        if (Integer.parseInt(doB[2]) < 0) return false;
        return true;
    }

    private boolean addrValidate(String addr) {
        if (addr == null || addr.equals("")) return false;
        return true;
    }



}
