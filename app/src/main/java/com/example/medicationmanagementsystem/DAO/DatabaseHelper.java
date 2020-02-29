package com.example.medicationmanagementsystem.DAO;
//code below is based on AndroidSQLite Tutorial Android CRUD Tutorial with SQLite (Create, Read, Update, Delete), ProgrammingKnowledge,
//https://www.youtube.com/watch?v=kDZES1wtKUY

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.medicationmanagementsystem.Patient;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Create Database
    public static final String DATABASE_NAME = "ManagementSystem.db";

    //create user table
    public static final String TABLE_USERS = "user_table";
    public static final String COL_USER_USERID = "USERID";
    public static final String COL_USER_EMAIL ="EMAIL";
    public static final String COL_USER_PASSWORD = "PASSWORD";
    public static final String COL_USER_TYPEOFUSER =  "TYPEOFUSER";
    //create caring list table
    public static final String TABLE_CARINGLIST = "caringlist_table";
    public static final String COL_CARINGLIST_ID = "CARINGLISTID";
    public static final String COL_CARINGLIST_TIMESLOT = "TIMESLOT";
    public static final String COL_CARINGLIST_NURSEID = "NURSEID";
    //Create patient table
    public static final String TABLE_PATIENT = "patient_table";
    public static final String COL_PATIENT_PATIENTID = "PATIENTID";
    public static final String COL_PATIENT_FNAME = "FNAME";
    public static final String COL_PATIENT_SNAME = "SNAME";
    public static final String COL_PATIENT_PPS = "PPS";
    public static final String COL_PATIENT_DOB = "DOB";
    public static final String COL_PATIENT_ADDRESS = "ADDRESS";
    public static final String COL_PATIENT_TYPE = "PATIENTTYPE";
    public static final String COL_PATIENT_MEDCOND = "PATIENTMEDCON";
    public static final String COL_PATIENT_CARINGID = COL_CARINGLIST_ID;

    //Create prescription table
    public static final String TABLE_PRESCRIPTION = "prescription_table";
    public static final String COL_PRESCRIPTION_ID = "PRESCRIPTIONID";
    public static final String COL_PRESCRIPTION__PATIENTID = COL_PATIENT_PATIENTID;
    public static final String COL_PRESCRIPTION__DATE = "DATE";
    public static final String COL_PRESCRIPTION__DRUGNAME = "DRUGNAME";
    public static final String COL_PRESCRIPTION__CONCENTRATION = "CONCENTRATION";
    public static final String COL_PRESCRIPTION__DOSAGE = "DOSAGE";
    public static final String COL_PRESCRIPTION__PREPARATION = "PREPARATION";
    public static final String COL_PRESCRIPTION__STARTDATE = "STARTDATE";
    public static final String COL_PRESCRIPTION__ENDDATE = "ENDDATE";
    public static final String COL_PRESCRIPTION__DOCTORID = "DOCTORID";



    public DatabaseHelper(Context context) {
        //Creating instance of database, version 1
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate (SQLiteDatabase db) {
        //create user table with SQL statement
        String usertable = "CREATE TABLE " + TABLE_USERS + "(USERID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, PASSWORD TEXT, TYPEOFUSER TEXT)";
        //create caringlist table
        String caringlisttable ="CREATE TABLE " + TABLE_CARINGLIST + "(CARINGLISTID INTEGER PRIMARY KEY AUTOINCREMENT, TIMESLOT TIME, NURSEID INTEGER)";
        //Create patienttable with SQL statement
        String patienttable = "CREATE TABLE " + TABLE_PATIENT + "(PATIENTID INTEGER PRIMARY KEY AUTOINCREMENT, FNAME TEXT, SNAME TEXT, PPS TEXT, DOB TEXT, ADDRESS TEXT, PATIENTTYPE TEXT, PATIENTMEDCON TEXT, CARINGLISTID INTEGER)";
        //Create prescriptiontable with SQL statement
        String prescriptiontable = "CREATE TABLE " + TABLE_PRESCRIPTION + "(PRESCRIPTIONID INTEGER PRIMARY KEY AUTOINCREMENT, PATIENTID INTEGER, DATE TEXT, DRUGNAME TEXT, CONCENTRATION TEXT, DOSAGE TEXT, PREPARATION TEXT, STARTDATE TEXT, ENDDATE TEXT, DOCTORID INTEGER)";
        //execute SQL statements
        db.execSQL(usertable);
        db.execSQL(caringlisttable);
        db.execSQL(patienttable);
        db.execSQL(prescriptiontable);
    }
    //When database version number is changed, database will update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARINGLIST);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRESCRIPTION);
        onCreate(db);
    }
    //code below is based on Login and Register | Sqlite Database | Android Studio | Part 1, Just Another Tutorial, https://www.youtube.com/watch?v=1WPAXHhG6u0
    //insert user data
    public boolean insertUserData(String email, String password, String typeofuser){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(COL_USER_EMAIL, email);
        contentValues3.put(COL_USER_PASSWORD,password);
        contentValues3.put(COL_USER_TYPEOFUSER, typeofuser);
        long result = db.insert(TABLE_USERS,null, contentValues3);
        if (result == 1)
            return false;
        else
            return true;
    }
    //check if email exists for user
    public boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user_table WHERE EMAIL=?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
     }

     //checking the email and password
    public Boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user_table WHERE EMAIL=? and PASSWORD=?", new String[]{email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //END
    public String checkUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT TYPEOFUSER from user_table WHERE EMAIL = ?",
                new String[] {email}
        );
        if (res.moveToNext()) {
            return res.getString(0);
        } else {
            return "";
        }
    }
    //getting email
    public String getEmail() throws SQLException {
        String email = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_USERS, new String[] {COL_USER_EMAIL},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                email = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return email;
    }

    //insert caringlist data
    public boolean insertCaringListData(String timeslot, String nurseid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(COL_CARINGLIST_TIMESLOT, timeslot);
        contentValues3.put(COL_CARINGLIST_NURSEID, nurseid);
        long result = db.insert(TABLE_CARINGLIST, null, contentValues3);
        if (result == 1)
            return false;
        else
            return true;
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

    //The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=8K-6gdTlGEA
    //view caring list info
    public Cursor getCaringListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor caringlistdata = db.rawQuery("SELECT CARINGLISTID, TIMESLOT FROM " + TABLE_CARINGLIST, null );
        return caringlistdata;
    }

    //view prescription details in a listview
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        //SQL select statement
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_PRESCRIPTION, null);
        return data;
    }
    //END
    //The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=8K-6gdTlGEA
    public Cursor getPatientContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        //SQL select statement
        Cursor patientdata = db.rawQuery("SELECT * FROM " + TABLE_PATIENT, null);
        return patientdata;
    }
    //END
    public Cursor getPrescriptionsPerPatient(String patientid){
        SQLiteDatabase db = this.getReadableDatabase();
        //SQL select statement
        Cursor data = db.rawQuery("SELECT * FROM prescription_table WHERE PATIENTID = ?",
                new String[]{patientid});
        return data;
    }
    public Cursor getPatientsPerCL(String caringlistid){
        SQLiteDatabase db = this.getReadableDatabase();
        //SQL select statement
        Cursor patients = db.rawQuery("SELECT * FROM patient_table WHERE CARINGLISTID = ?",
                new String[]{caringlistid});
        return patients;
    }
    //get caring lists per nurse
    public Cursor getCaringlistPerNurse(String nurseid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor caringlists = db.rawQuery("SELECT CARINGLISTID, TIMESLOT FROM caringlist_table WHERE NURSEID = ?",
                new String[]{nurseid});
        return caringlists;
    }
    //getting nurse id from email login
    public String getNurseID(String email) {
        String nurseid = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT USERID FROM user_table WHERE EMAIL=?", new String[]{email});
        if (cursor.moveToFirst()) {
            do {
                nurseid = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return nurseid;
    }

    //This code is based on SQLite Database to ListView- Part 4:Search Items- Android Studio Tutorial, KOD Dev, https://www.youtube.com/watch?v=QY-O49a_Ags
    public Cursor searchPrescriptions(String text) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_PRESCRIPTION + "WHERE " + COL_PRESCRIPTION_ID + " Like '%" + text +"%'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    //END
    //code below based on Android SQLite Database Tutorial 5 # Update values in SQLite Database table using Android,
    // ProgrammingKnowledge, https://www.youtube.com/watch?v=PA4A9IesyCg&t=783s
    //Remove patient from caringlist
    public boolean UpdateCaringList(String patientid, String caringlistid) {
        caringlistid = "0";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(COL_PATIENT_CARINGID, caringlistid);
        long result= db.update(TABLE_PATIENT,contentValues3,"PATIENTID = ?",new String[]{patientid});
        return true;
    }
    //END
    //Updating patient information
    public boolean updatePatientData(String patientid, String fname, String sname, String pps, String dob, String address, String patienttype, String patientmedcon, String caringid) {

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
    long result = db.update(TABLE_PATIENT, contentValues1, "PATIENTID = ? ", new String[]{patientid});
    return true;
    }
    //retrieving patient information
    public Cursor getPatient(String patientid){
        SQLiteDatabase db = this.getReadableDatabase();
        //SQL select statement
        Cursor patientdata = db.rawQuery("SELECT * FROM patient_table WHERE PATIENTID = ?",
                new String[]{patientid});
        patientdata.moveToFirst();
        return patientdata;
    }
}



