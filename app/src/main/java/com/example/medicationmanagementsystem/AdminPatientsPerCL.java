package com.example.medicationmanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

import java.util.ArrayList;

public class AdminPatientsPerCL extends AppCompatActivity {
    DatabaseHelper myDB;
    ArrayList<Patient> patientList;
    ListView patientlistView;
    Patient patient;
    ArrayAdapter adapter;
    ArrayList<String> patientlistItem = new ArrayList<String>();
    Button btnAddPatientsToCaring;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setting layout
        setContentView(R.layout.admin_viewpatients);
        ListView patientlistView = (ListView)findViewById(R.id.patientlview);
        btnAddPatientsToCaring = (Button)findViewById(R.id.btnAddNewPatients);
        //The code below based on android listview header, CodeVideo, https://www.youtube.com/watch?v=EnG5ZIVfki8
        ViewGroup headerview = (ViewGroup)getLayoutInflater().inflate(R.layout.viewpatient_header, patientlistView, false);
        patientlistView.addHeaderView(headerview);
        //END
        myDB = new DatabaseHelper(this);
        patientlistItem = new ArrayList<String>();
        //create new array
        patientList = new ArrayList<Patient>();
        Cursor patientdata = myDB.getPatientContents();
        int numRows = patientdata.getCount();
        //if there is no rows in the database
        if(numRows == 0){
            Toast.makeText(AdminPatientsPerCL.this, "There is nothing in this database", Toast.LENGTH_LONG).show();
        }
        else {
            while(patientdata.moveToNext()){
                patient = new Patient(patientdata.getString(0), patientdata.getString(1), patientdata.getString(2),
                        patientdata.getString(3), patientdata.getString(4),patientdata.getString(5), patientdata.getString(6),
                        patientdata.getString(7), patientdata.getString(8));
                patientList.add(patient);
            }
            Patient_ListAdapter padapter = new Patient_ListAdapter(this,R.layout.patient_view,patientList);
            patientlistView = (ListView)findViewById(R.id.patientlview);
            patientlistView.setAdapter(padapter);
        }
        btnAddPatientsToCaring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createcaringlist= new Intent(AdminPatientsPerCL.this, NewCaringList.class);
                startActivity(createcaringlist);
            }}
        );
    }
    //END
}