package com.example.medicationmanagementsystem;
//The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=jpt3Md9aDIQ
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class Patient_ListAdapter extends ArrayAdapter<Patient> {
    private LayoutInflater pInflater;
    private ArrayList<Patient> patients;
    private int pViewResourceId;

    public Patient_ListAdapter(Context context, int textViewResourceId, ArrayList<Patient> patients) {
        super(context, textViewResourceId, patients);
        this.patients = patients;
        pInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        pViewResourceId = textViewResourceId;

    }
    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = pInflater.inflate(pViewResourceId, null);

        Patient patient = patients.get(position);
        if (patient != null) {
            TextView patientid = (TextView) convertView.findViewById(R.id.textPatientID);
            TextView Fname = (TextView) convertView.findViewById(R.id.textFname);
            TextView Sname = (TextView) convertView.findViewById(R.id.textSname);
            TextView pps = (TextView) convertView.findViewById(R.id.textpps);
            TextView dob = (TextView) convertView.findViewById(R.id.textdob);
            TextView address = (TextView) convertView.findViewById(R.id.textaddress);
            TextView patienttype = (TextView) convertView.findViewById(R.id.textpatienttype);
            TextView medcon = (TextView) convertView.findViewById(R.id.textmedcon);
            TextView caringid = (TextView) convertView.findViewById(R.id.textcaringid);

        if(patientid != null) {
            patientid.setText((patient.getPatientID()));
        }
        if(Fname != null) {
            Fname.setText((patient.getFName()));
        }
        if(Sname!= null) {
            Sname.setText((patient.getSName()));
        }
        if(pps != null) {
            pps.setText((patient.getPps()));
        }
        if(dob != null) {
            dob.setText((patient.getDob()));
        }
        if(address != null) {
            address.setText((patient.getAddress()));
        }
        if(patienttype != null) {
            patienttype.setText((patient.getPatientType()));
        }
        if(medcon != null) {
            medcon.setText((patient.getMedcon()));
        }
        if(caringid != null) {
            caringid.setText((patient.getCaringlistid()));
        }
    }
    return convertView;
}
}
//END
