package com.example.medicationmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
    Button btnSubmitData;

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
        AddData();



        //END
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