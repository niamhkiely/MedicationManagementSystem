package com.example.medicationmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

import java.util.ArrayList;

public class PatientsPerCaringList extends AppCompatActivity {
    DatabaseHelper myDB;
    ArrayList<Patient> patientList;
    ListView patientlistView;
    Patient patient;
    ArrayAdapter adapter;
    ArrayList<String> patientlistItem = new ArrayList<String>();
    TextView txtpatientid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setting layout
        setContentView(R.layout.viewpatientcontents);
        ListView patientlistView = (ListView) findViewById(R.id.patientlistview);
        //The code below based on android listview header, CodeVideo, https://www.youtube.com/watch?v=EnG5ZIVfki8
        ViewGroup headerview = (ViewGroup)getLayoutInflater().inflate(R.layout.viewpatient_header, patientlistView, false);
        patientlistView.addHeaderView(headerview);
        //END
        myDB = new DatabaseHelper(this);

        patientlistItem = new ArrayList<String>();
        //create new array
        patientList = new ArrayList<Patient>();
        Intent mainintent = getIntent();
        String caringlistid = mainintent.getStringExtra("CARINGLISTID");

        Cursor patients = myDB.getPatientsPerCL(caringlistid);
        int numRows = patients.getCount();
        //if there is no rows in the database
        if(numRows == 0){
            Toast.makeText(PatientsPerCaringList.this, "There are no patients in this caring list currently", Toast.LENGTH_LONG).show();
        }
        else {
            while (patients.moveToNext()) {
                patient = new Patient(patients.getString(0), patients.getString(1), patients.getString(2),
                        patients.getString(3), patients.getString(4), patients.getString(5), patients.getString(6),
                        patients.getString(7), patients.getString(8));
                patientList.add(patient);
            }
            Patient_ListAdapter padapter = new Patient_ListAdapter(this, R.layout.patient_view, patientList);
            patientlistView = (ListView)findViewById(R.id.patientlistview);
            patientlistView.setAdapter(padapter);
            UpdateCaringList();
        }}
        //code below based on Android SQLite Database Tutorial 5 # Update values in SQLite Database table using Android,
        //ProgrammingKnowledge, https://www.youtube.com/watch?v=PA4A9IesyCg&t=783s
        public void UpdateCaringList(){
        ListView patientlistView = (ListView)findViewById(R.id.patientlistview);
        patientlistView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //code below based on Android Tutorial for Beginners 12 # Android Alert Dialog Example, ProgrammingKnowledge
                //https://www.youtube.com/watch?v=oqEXYBepqus
                AlertDialog.Builder a_builder = new AlertDialog.Builder(PatientsPerCaringList.this);
                a_builder.setMessage("Do you want to delete patient from caring list?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtpatientid = (TextView)findViewById(R.id.textPatientID);
                                String patientid = txtpatientid.getText().toString();
                                String caringlistid = "0";
                                boolean isUpdated = myDB.UpdateCaringList(patientid, caringlistid);
                                if (isUpdated == true)
                                    Toast.makeText(PatientsPerCaringList.this, "Caring List Updated", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(PatientsPerCaringList.this, "Caring List Not Updated", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert");
                alert.show();
                //END
            }
        });
    }
}
//END
