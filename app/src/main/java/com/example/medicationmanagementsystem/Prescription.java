package com.example.medicationmanagementsystem;
//The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=jpt3Md9aDIQ
public class Prescription {
    //creating variables
    private String prescriptionid;
    private String patientID;
    private String presdate;
    private String drugname;
    private String concentration;
    private String dosage;
    private String preparation;
    private String startdate;
    private String enddate;
    private String doctorID;


    public Prescription(String presID, String pID, String preDate, String dname, String concent, String dos, String prep, String sDate, String eDate, String dID) {
        prescriptionid = presID;
        patientID = pID;
        presdate = preDate;
        drugname = dname;
        concentration = concent;
        dosage = dos;
        preparation = prep;
        startdate =sDate;
        enddate = eDate;
        doctorID = dID;
    }

    public String getPresID() { return prescriptionid; }

    public String getPatientID() {
        return patientID;
    }

    public String getPresdate() {
        return presdate;
    }

    public String getDrugname() {
        return drugname;
    }

    public String getConcentration() {
        return concentration;
    }

    public String getDosage() {
        return dosage;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public static class NewPatient {
    }
}
//END