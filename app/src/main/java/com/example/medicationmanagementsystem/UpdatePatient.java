package com.example.medicationmanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

public class UpdatePatient extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editfname, editsname, editpps, editdob, editaddress, editpatienttype, editpatientmedcon, editcaringid;
    Button btnSubmitPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_patient);

        myDb = new DatabaseHelper(this);
        Intent mainintent = getIntent();
        String patientid = mainintent.getStringExtra("PATIENTID");
        Cursor patientdata = myDb.getPatient(patientid);

        editfname = findViewById(R.id.txtfname);
        editfname.setText(patientdata.getString(1));
        editsname = findViewById(R.id.txtSName);
        editsname.setText(patientdata.getString(2));
        editpps = findViewById(R.id.txtPPS);
        editpps.setText(patientdata.getString(3));
        editdob = findViewById(R.id.txtDOB);
        editdob.setText(patientdata.getString(4));
        editaddress = findViewById(R.id.txtAddress);
        editaddress.setText(patientdata.getString(5));
        editpatienttype = findViewById(R.id.txtTypeOfPatient);
        editpatienttype.setText(patientdata.getString(6));
        editpatientmedcon = findViewById(R.id.txtMedConditions);
        editpatientmedcon.setText(patientdata.getString(7));
        editcaringid = findViewById(R.id.txtCaringID);
        editcaringid.setText(patientdata.getString(8));
        btnSubmitPatient = findViewById(R.id.btnSubmitPatient);
        UpdatePatientData();

    }
    public void UpdatePatientData() {
        //When user clicks submit button
        btnSubmitPatient.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent mainintent = getIntent();
                        String patientid = mainintent.getStringExtra("PATIENTID");
                        boolean isUpdated =  myDb.updatePatientData(patientid, editfname.getText().toString(),
                                editsname.getText().toString(), editpps.getText().toString(),
                                editdob.getText().toString(), editaddress.getText().toString(),
                                editpatienttype.getText().toString(), editpatientmedcon.getText().toString(),
                                editcaringid.getText().toString());
                        if(isUpdated = true)
                            Toast.makeText(UpdatePatient.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(UpdatePatient.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
//END
