package com.example.medicationmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medicationmanagementsystem.DAO.DatabaseHelper;
import java.util.ArrayList;
//The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=hHQqFGpod14
public class AdminPatientsPerCL extends AppCompatActivity {
    DatabaseHelper myDB;
    ArrayList<Patient> patientList;
    ListView patientlistView;
    Patient patient;
    ArrayAdapter adapter;
    ArrayList<String> patientlistItem = new ArrayList<String>();
    Button btnAddPatientsToCaring;
    TextView txtpatientid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setting layout
        setContentView(R.layout.admin_viewpatients);
        ListView patientlistView = (ListView)findViewById(R.id.patientlview);

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

        Cursor patientdata = myDB.getPatientsPerCL(caringlistid);
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
            UpdateCaringList();
        }}
        //END
        //code below based on Android SQLite Database Tutorial 5 # Update values in SQLite Database table using Android,
        //ProgrammingKnowledge, https://www.youtube.com/watch?v=PA4A9IesyCg&t=783s
        public void UpdateCaringList(){
            ListView patientlistView = (ListView)findViewById(R.id.patientlview);
            patientlistView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    txtpatientid = (TextView)view.findViewById(R.id.textPatientID);
                    //code below based on Android Tutorial for Beginners 12 # Android Alert Dialog Example, ProgrammingKnowledge
                    //https://www.youtube.com/watch?v=oqEXYBepqus
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(AdminPatientsPerCL.this);
                    a_builder.setMessage("Do you want to delete patient from caring list?").setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //txtpatientid = (TextView)findViewById(R.id.textPatientID);
                                    String patientid = txtpatientid.getText().toString();
                                    String caringlistid = "0";
                                    boolean isUpdated = myDB.UpdateCaringList(patientid, caringlistid);
                                    if (isUpdated == true)
                                        Toast.makeText(AdminPatientsPerCL.this, "Caring List Updated", Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(AdminPatientsPerCL.this, "Caring List Not Updated", Toast.LENGTH_LONG).show();
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
                }
            });
            //END
    }

}