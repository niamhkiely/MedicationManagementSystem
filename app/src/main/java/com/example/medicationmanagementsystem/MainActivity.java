package com.example.medicationmanagementsystem;
////code below is based on AndroidSQLite Tutorial Android CRUD Tutorial with SQLite (Create, Read, Update, Delete), ProgrammingKnowledge, https://www.youtube.com/watch?v=kDZES1wtKUY
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editpatientid, editdate, editdrugname, editdosage, editconcentration, editprep, editstartdate, editenddate, editdoctorid;
    Button btnSubmitData, btnViewPrescription, btnAddPatient;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editpatientid = findViewById(R.id.txtpatientno);
        editdate = findViewById(R.id.txtdate);
        editdrugname = findViewById(R.id.txtDrugname);
        editconcentration = findViewById(R.id.txtConcentration);
        editdosage = findViewById(R.id.txtDosage);
        editprep = findViewById(R.id.txtPreparation);
        editstartdate = findViewById(R.id.txtSDate);
        editenddate = findViewById(R.id.txtEndDate);
        editdoctorid = findViewById(R.id.txtDrNumber);
        btnSubmitData = findViewById(R.id.btnSubmitPrescription);
        btnViewPrescription = findViewById(R.id.btnView);
        btnAddPatient = findViewById(R.id.btnAddPatient);
        AddData();

        btnViewPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });


        //END
        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PatientIntent = new Intent(MainActivity.this, NewPatient.class);
                startActivity(PatientIntent);
            }
        });

        //the code below is based on Show Current Date in android textView using Android Studio, Devodex, https://www.youtube.com/watch?v=myhyZaAfJ-c
        String date_n = new SimpleDateFormat("MMM,dd,yyyy", Locale.getDefault()).format(new Date());
        TextView tv_date = findViewById(R.id.mydateText);
        tv_date.setText(date_n);
        //END

    }

    public void AddData() {
        btnSubmitData.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editpatientid.getText().toString(), editdate.getText().toString(),
                                editdrugname.getText().toString(), editconcentration.getText().toString(),
                                editdosage.getText().toString(), editprep.getText().toString(),
                                editstartdate.getText().toString(),
                                editenddate.getText().toString(), editdoctorid.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
//END