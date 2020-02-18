package com.example.medicationmanagementsystem;
//The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=jpt3Md9aDIQ
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EightColumn_ListAdapter extends ArrayAdapter<Prescription> {

  private LayoutInflater mInflater;
  private ArrayList<Prescription> prescriptions;
  private int mViewResourceId;

  public EightColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<Prescription> prescriptions) {
    super(context, textViewResourceId, prescriptions);
    this.prescriptions = prescriptions;
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mViewResourceId = textViewResourceId;

  }

  public View getView(int position, View convertView, ViewGroup parents) {
    convertView = mInflater.inflate(mViewResourceId, null);

    Prescription prescription = prescriptions.get(position);

    if (prescription != null) {
      TextView prescriptionID = (TextView) convertView.findViewById(R.id.textprescriptionid);
      TextView patientID = (TextView) convertView.findViewById(R.id.textpatientID);
      TextView date = (TextView) convertView.findViewById(R.id.textdate);
      TextView drugname = (TextView) convertView.findViewById(R.id.textdrugname);
      TextView concentration = (TextView) convertView.findViewById(R.id.textcontentration);
      TextView dosage = (TextView) convertView.findViewById(R.id.textdosage);
      TextView prep = (TextView) convertView.findViewById(R.id.textprep);
      TextView startdate = (TextView) convertView.findViewById(R.id.textstartdate);
      TextView enddate = (TextView) convertView.findViewById(R.id.textenddate);
      TextView doctorID = (TextView) convertView.findViewById(R.id.textdoctorid);

      if(prescriptionID != null) {
        prescriptionID.setText((prescription.getPresID()));
      }
      if(patientID != null) {
        patientID.setText((prescription.getPatientID()));
      }
      if(date != null) {
        date.setText((prescription.getPresdate()));
      }
      if(drugname != null) {
        drugname.setText((prescription.getDrugname()));
      }
      if(concentration != null) {
        concentration.setText((prescription.getConcentration()));
      }
      if(dosage != null) {
        dosage.setText((prescription.getDosage()));
      }
      if(prep != null) {
        prep.setText((prescription.getPreparation()));
      }
      if(startdate != null) {
        startdate.setText((prescription.getStartdate()));
      }
      if(enddate != null) {
        enddate.setText((prescription.getEnddate()));
      }
      if(doctorID != null) {
        patientID.setText((prescription.getDoctorID()));
      }
    }
    return convertView;
  }
}
//END