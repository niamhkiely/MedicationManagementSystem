package com.example.medicationmanagementsystem;
//code below is based on AndroidSQLite Tutorial Android CRUD Tutorial with SQLite (Create, Read, Update, Delete), ProgrammingKnowledge,
//https://www.youtube.com/watch?v=kDZES1wtKUY


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
    EditText editpatientid, editdrugname, editdosage, editconcentration, editprep, editdoctorid;
    TextView editdate, editstartdate, editenddate;
    Button btnSubmitData;
    private DatePickerDialog.OnDateSetListener mDateSetLisener;
    private DatePickerDialog.OnDateSetListener nDateSetLisener;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editpatientid = findViewById(R.id.txtpatientno);
        editdate = findViewById(R.id.mydateText);
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
        //code below is based on Android Beginner Tutorial #25- DatePicker Dialog [Choosing a Date from a Dialog Picker,
        //CodingWithMitch, https://www.youtube.com/watch?v=hwe1abDO2Ag
        editstartdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetLisener, year, month, day);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        editenddate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, nDateSetLisener, year, month, day);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetLisener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date1 = dayOfMonth + "/" + month + "/" + year;
                editstartdate.setText(date1);
                editstartdate.setTextColor(Color.BLACK);

            }
        };

        nDateSetLisener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date2 = dayOfMonth + "/" + month + "/" + year;
               editenddate.setText(date2);
                editenddate.setTextColor(Color.BLACK);
            }
        };
        //END
        //the code below is based on Show Current Date in android textView using Android Studio, Devodex,
        // https://www.youtube.com/watch?v=myhyZaAfJ-c
        String date_n = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault()).format(new Date());
        TextView tv_date = findViewById(R.id.mydateText);
        tv_date.setText(date_n);
        tv_date.setTextSize(15);
    }

    //END

    //code below is based on AndroidSQLite Tutorial Android CRUD Tutorial with SQLite (Create, Read, Update, Delete), ProgrammingKnowledge,
    //https://www.youtube.com/watch?v=kDZES1wtKUY
    public void AddData() {
        btnSubmitData.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        String seditpatientid = editpatientid.getText().toString();
                        String seditdate = editdate.getText().toString();
                        String seditdrugname = editdrugname.getText().toString();
                        String seditconcentration = editdrugname.getText().toString();
                        String seditdosage = editdosage.getText().toString();
                        String seditprep = editprep.getText().toString();
                        String seditstartdate = editstartdate.getText().toString();
                        String seditenddate = editenddate.getText().toString();
                        String seditdoctorid = editdoctorid.getText().toString();

                        if (seditpatientid.equals("") || seditdate.equals("") || seditdrugname.equals("") || seditconcentration.equals("")
                                || seditdosage.equals("") || seditprep.equals("") || seditstartdate.equals("") || seditenddate.equals("")
                                || seditdoctorid.equals("")) {
                            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean isInserted = myDb.insertData(editpatientid.getText().toString(), editdate.getText().toString(),
                                    editdrugname.getText().toString(), editconcentration.getText().toString(),
                                    editdosage.getText().toString(), editprep.getText().toString(),
                                    editstartdate.getText().toString(),
                                    editenddate.getText().toString(), editdoctorid.getText().toString());
                            if (isInserted = true)
                                //show messagebox
                                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
//END