package com.example.medicationmanagementsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.medicationmanagementsystem.DAO.DatabaseHelper;
import com.google.android.material.navigation.NavigationView;
import java.util.Calendar;

public class NewPatient extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
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
        //navigation drawer
        //code below is based on Navigation Drawer with Fragments Part 2- Layout and Hamburger Icon, Coding In Flow, https://www.youtube.com/watch?v=zYVEMCiDcmY
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //END

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
        //code below is based on AndroidSQLite Tutorial Android CRUD Tutorial with SQLite (Create, Read, Update, Delete), ProgrammingKnowledge, https://www.youtube.com/watch?v=kDZES1wtKUY
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
    //END
    //the code below is based on Text Spinner- Android Studio Tutorial, Coding In Flow, https://www.youtube.com/watch?v=on_OrrX7Nw4
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
    //END
    //code below is based on Navigation Drawer with Fragments Part 3- Handling menu items clicks, Coding In Flow, https://www.youtube.com/watch?v=bjYstsO1PgI
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_createprescription:
                Intent createpresintent = new Intent(NewPatient.this, MainActivity.class);
                startActivity(createpresintent);
                break;
            case R.id.viewprescriptions:
                Intent presintent = new Intent(NewPatient.this, ViewListContents.class);
                startActivity(presintent);
                break;
            case R.id.nav_addpatients:
                Intent createpatientintent = new Intent(NewPatient.this, NewPatient.class);
                startActivity(createpatientintent);
                break;
            case R.id.nav_viewpatients:
                Intent viewpatientintent = new Intent(NewPatient.this, ViewPatientListContents.class);
                startActivity(viewpatientintent);
                break;
            case R.id.nav_logout:
                Intent logoutintent= new Intent(NewPatient.this, Login.class);
                startActivity(logoutintent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //END
}
//END

