package com.example.medicationmanagementsystem;
//code below is based on AndroidSQLite Tutorial Android CRUD Tutorial with SQLite (Create, Read, Update, Delete), ProgrammingKnowledge, https://www.youtube.com/watch?v=kDZES1wtKUY
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

import java.util.Calendar;

public class NewPatient extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DatabaseHelper myDb;
    EditText editfname, editsname, editpps, editaddress, editpatientmedcon, editcaringid;
    TextView editdob;
    Spinner editpatienttype;
    Button btnSubmitPatient;
    private DatePickerDialog.OnDateSetListener mDateSetLisener;
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
        editpatientmedcon = findViewById(R.id.txtMedConditions);
        editcaringid = findViewById(R.id.txtCaringID);
        btnSubmitPatient = findViewById(R.id.btnSubmitPatient);
        AddPatientData();

        //spinner for type of patient
        //the code below is based on Text Spinner- Android Studio Tutorial, Coding In Flow, https://www.youtube.com/watch?v=on_OrrX7Nw4
        editpatienttype = (Spinner)findViewById(R.id.txtTypeOfPatient);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.typeofpatient, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editpatienttype.setAdapter(adapter);
        editpatienttype.setOnItemSelectedListener(this);
        //END

        //code below is based on Android Beginner Tutorial #25- DatePicker Dialog [Choosing a Date from a Dialog Picker,
        //CodingWithMitch, https://www.youtube.com/watch?v=hwe1abDO2Ag
        editdob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(NewPatient.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetLisener, year,month,day);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetLisener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                editdob.setText(date);
                editdob.setTextColor(Color.BLACK);
            }
        };
        }
        //END
    public void AddPatientData() {
        //When user clicks submit button
        btnSubmitPatient.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String fname = editfname.getText().toString();
                        String sname = editsname.getText().toString();
                        String pps = editpps.getText().toString();
                        String dob = editdob.getText().toString();
                        String address = editaddress.getText().toString();
                        String patienttype = editpatienttype.getSelectedItem().toString();
                        String medcon = editpatientmedcon.getText().toString();
                        String caringlistid = editcaringid.getText().toString();
                        if (fname.equals("") || sname.equals("") || pps.equals("") || dob.equals("")
                                || address.equals("") ||  patienttype.equals("") || medcon.equals("") || caringlistid.equals("")) {
                            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                        } else {

                            boolean isInserted = myDb.insertPatientData(editfname.getText().toString(),
                                    editsname.getText().toString(), editpps.getText().toString(),
                                    editdob.getText().toString(), editaddress.getText().toString(),
                                    editpatienttype.getSelectedItem().toString(), editpatientmedcon.getText().toString(),
                                    editcaringid.getText().toString());
                            if (isInserted = true)
                                Toast.makeText(NewPatient.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(NewPatient.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView) view;
        if (position == 0) {
            tv.setText("Type of User");
            // Set the hint text color gray
            tv.setTextColor(Color.GRAY);
            tv.setTextSize(14);
            tv.setPadding(0,0,0,0);

        } else {
            tv.setTextColor(Color.BLACK);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
//END

