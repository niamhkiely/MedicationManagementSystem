package com.example.medicationmanagementsystem;
//code below is based on AndroidSQLite Tutorial Android CRUD Tutorial with SQLite (Create, Read, Update, Delete), ProgrammingKnowledge, https://www.youtube.com/watch?v=kDZES1wtKUY
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

public class NewPatient extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editfname, editsname, editpps, editdob, editaddress, editpatienttype, editpatientmedcon, editcaringid;
    Button btnSubmitPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_patient);

        myDb = new DatabaseHelper(this);

        editfname = findViewById(R.id.txtfname);
        editsname = findViewById(R.id.txtSName);
        editpps = findViewById(R.id.txtPPS);
        editdob = findViewById(R.id.txtDOB);
        editaddress = findViewById(R.id.txtAddress);
        editpatienttype = findViewById(R.id.txtTypeOfPatient);
        editpatientmedcon = findViewById(R.id.txtMedConditions);
        editcaringid = findViewById(R.id.txtCaringID);
        btnSubmitPatient = findViewById(R.id.btnSubmitPatient);
        AddPatientData();

    }
    public void AddPatientData() {
        btnSubmitPatient.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                       boolean isInserted =  myDb.insertPatientData(editfname.getText().toString(),
                                editsname.getText().toString(), editpps.getText().toString(),
                                editdob.getText().toString(), editaddress.getText().toString(),
                                editpatienttype.getText().toString(), editpatientmedcon.getText().toString(),
                                editcaringid.getText().toString());
                       if(isInserted = true)
                           Toast.makeText(NewPatient.this, "Data Inserted", Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(NewPatient.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
//END

