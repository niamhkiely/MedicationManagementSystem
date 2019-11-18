package com.example.medicationmanagementsystem.DAO;
//code below is based on AndroidSQLite Tutorial Android CRUD Tutorial with SQLite (Create, Read, Update, Delete), ProgrammingKnowledge, https://www.youtube.com/watch?v=kDZES1wtKUY
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Create Database
    public static final String DATABASE_NAME = "ManagementSystem.db";
    //Create patient table
    public static final String TABLE_PATIENT = "patient_table";
    public static final String COL_PATIENT_PATIENTID = "PATID";
    public static final String COL_PATIENT_FNAME = "FNAME";
    public static final String COL_PATIENT_SNAME = "SNAME";
    public static final String COL_PATIENT_PPS = "PPS";
    public static final String COL_PATIENT_DOB = "DOB";
    public static final String COL_PATIENT_ADDRESS = "ADDRESS";
    public static final String COL_PATIENT_TYPE = "PATIENTTYPE";
    public static final String COL_PATIENT_MEDCOND = "PATIENTMEDCON";
    public static final String COL_PATIENT_CARINGID = "CARINGID";

    //Create prescription table
    public static final String TABLE_PRESCRIPTION = "prescription_table";
    public static final String COL_PRESCRIPTION_ID = "PRESCRIPTIONID";
    public static final String COL_PRESCRIPTION__PATIENTID = "PATIENTID";
    public static final String COL_PRESCRIPTION__DATE = "DATE";
    public static final String COL_PRESCRIPTION__DRUGNAME = "DRUGNAME";
    public static final String COL_PRESCRIPTION__CONCENTRATION = "CONCENTRATION";
    public static final String COL_PRESCRIPTION__DOSAGE = "DOSAGE";
    public static final String COL_PRESCRIPTION__PREPARATION = "PREPARATION";
    public static final String COL_PRESCRIPTION__STARTDATE = "STARTDATE";
    public static final String COL_PRESCRIPTION__ENDDATE = "ENDDATE";
    public static final String COL_PRESCRIPTION__DOCTORID = "DOCTORID";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate (SQLiteDatabase db) {
        String patienttable = "CREATE TABLE " + TABLE_PATIENT + "(PATID INTEGER PRIMARY KEY AUTOINCREMENT, FNAME TEXT, SNAME TEXT, PPS TEXT, DOB TEXT, ADDRESS TEXT, PATIENTTYPE TEXT, PATIENTMEDCON TEXT, CARINGID INTEGER)";
        String prescriptiontable = "CREATE TABLE " + TABLE_PRESCRIPTION + "(PRESCRIPTIONID INTEGER PRIMARY KEY AUTOINCREMENT, PATIENTID INTEGER, DATE TEXT, DRUGNAME TEXT, CONCENTRATION TEXT, DOSAGE TEXT, PREPARATION TEXT, STARTDATE TEXT, ENDDATE TEXT, DOCTORID INTEGER)";
        db.execSQL(patienttable);
        db.execSQL(prescriptiontable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRESCRIPTION);
        onCreate(db);
    }
    //insert patient data
    public boolean insertPatientData(String fname, String sname, String pps, String dob, String address, String patienttype, String patientmedcon, String caringid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COL_PATIENT_FNAME, fname);
        contentValues1.put(COL_PATIENT_SNAME, sname);
        contentValues1.put(COL_PATIENT_PPS, pps);
        contentValues1.put(COL_PATIENT_DOB, dob);
        contentValues1.put(COL_PATIENT_ADDRESS, address);
        contentValues1.put(COL_PATIENT_TYPE, patienttype);
        contentValues1.put(COL_PATIENT_MEDCOND,patientmedcon);
        contentValues1.put(COL_PATIENT_CARINGID, caringid);
        long result= db.insert(TABLE_PATIENT,null, contentValues1);
        if (result == 1)
            return false;
        else
            return true;
    }
    //insert prescription data
    public boolean insertData(String patientid, String date, String drugname, String concentration,String dosage, String preparation, String startdate, String enddate, String doctorid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(COL_PRESCRIPTION__PATIENTID, patientid);
        contentValues2.put(COL_PRESCRIPTION__DATE, date);
        contentValues2.put(COL_PRESCRIPTION__DRUGNAME, drugname);
        contentValues2.put(COL_PRESCRIPTION__CONCENTRATION, concentration);
        contentValues2.put(COL_PRESCRIPTION__DOSAGE, dosage);
        contentValues2.put(COL_PRESCRIPTION__PREPARATION, preparation);
        contentValues2.put(COL_PRESCRIPTION__STARTDATE, startdate);
        contentValues2.put(COL_PRESCRIPTION__ENDDATE, enddate);
        contentValues2.put(COL_PRESCRIPTION__DOCTORID, doctorid);
        long result= db.insert(TABLE_PRESCRIPTION,null, contentValues2);
        if (result == 1)
            return false;
        else
            return true;
        //END

    }

    //Coding with mitch tutorial
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_PRESCRIPTION, null);
        return data;
    }
}

