package com.example.medicationmanagementsystem;
//The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=hHQqFGpod14

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

import java.util.ArrayList;

public class ViewPatientListContents extends AppCompatActivity {
    DatabaseHelper myDB;
    ArrayList<Patient> patientList;
    ListView patientlistView;
    Patient patient;
    ArrayAdapter adapter;
    ArrayList<String> patientlistItem = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setting layout
        setContentView(R.layout.viewpatientcontents);

        myDB = new DatabaseHelper(this);
        patientlistItem = new ArrayList<String>();
        //create new array
        patientList = new ArrayList<Patient>();
        Cursor patientdata = myDB.getPatientContents();
        int numRows = patientdata.getCount();
        //if there is no rows in the database
        if(numRows == 0){
            Toast.makeText(ViewPatientListContents.this, "There is nothing in this database", Toast.LENGTH_LONG).show();
        }
        else {
            while(patientdata.moveToNext()){
                patient = new Patient(patientdata.getString(0), patientdata.getString(1), patientdata.getString(2),
                        patientdata.getString(3), patientdata.getString(4),patientdata.getString(5), patientdata.getString(6),
                        patientdata.getString(7), patientdata.getString(8));
                patientList.add(patient);
            }
            Patient_ListAdapter padapter = new Patient_ListAdapter(this,R.layout.patient_view,patientList);
            patientlistView = (ListView) findViewById(R.id.patientlistview);
            patientlistView.setAdapter(padapter);
        }
    }
    //END
}
