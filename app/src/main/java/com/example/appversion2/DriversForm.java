package com.example.appversion2;

import java.text.Normalizer;

public class DriversForm extends FormSubmission {
    String gLevel;
    public DriversForm(String g, String fName, String lName, String dob, String addr, String ft) {
        super(fName, lName, dob, addr, ft);
        gLevel = g;
    }

    public String getgLevel() {
        return gLevel;
    }
    public void setgLevel(String ngl) {
        gLevel = ngl;
    }
}
