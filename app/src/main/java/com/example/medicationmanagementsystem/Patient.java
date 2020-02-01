package com.example.medicationmanagementsystem;
//The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=jpt3Md9aDIQ
public class Patient {
    //creating variables
    private String patientid;
    private String fname;
    private String sname;
    private String pps;
    private String dob;
    private String address;
    private String patienttype;
    private String medcon;
    private String caringlistid;

    public Patient(String pID, String firstname, String secondname, String ppsno, String patientdob, String patientaddress, String ptype, String patientmedcon, String clistid) {
        patientid = pID;
        fname = firstname;
        sname = secondname;
        pps = ppsno;
        dob = patientdob;
        address = patientaddress;
        patienttype = ptype;
        medcon = patientmedcon;
        caringlistid = clistid;
    }

    public String getPatientID() {
        return patientid;
    }

    public String getFName() {
        return fname;
    }

    public String getSName() {
        return sname;
    }

    public String getPps() {
        return pps;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getPatientType() {
        return patienttype;
    }

    public String getMedcon() {
        return medcon;
    }

    public String getCaringlistid() {
        return caringlistid;
    }

}
