package com.example.medicationmanagementsystem.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Create Database
    public static final String DATABASE_NAME = "ManagementSystem.db";

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
        db.execSQL("create table "+ TABLE_PRESCRIPTION + " (PRESCRIPTIONID INTEGER PRIMARY KEY AUTOINCREMENT, PATIENTID INTEGER, DATE TEXT, DRUGNAME TEXT, CONCENTRATION TEXT, DOSAGE TEXT, PREPARATION TEXT, STARTDATE TEXT, ENDDATE TEXT, DOCTORID INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRESCRIPTION);
        onCreate(db);
    }
    public boolean insertData(String patientid, String date, String drugname, String concentration,String dosage, String preparation, String startdate, String enddate, String doctorid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PRESCRIPTION__PATIENTID, patientid);
        contentValues.put(COL_PRESCRIPTION__DATE, date);
        contentValues.put(COL_PRESCRIPTION__DRUGNAME, drugname);
        contentValues.put(COL_PRESCRIPTION__CONCENTRATION, concentration);
        contentValues.put(COL_PRESCRIPTION__DOSAGE, dosage);
        contentValues.put(COL_PRESCRIPTION__PREPARATION, preparation);
        contentValues.put(COL_PRESCRIPTION__STARTDATE, startdate);
        contentValues.put(COL_PRESCRIPTION__ENDDATE, enddate);
        contentValues.put(COL_PRESCRIPTION__DOCTORID, doctorid);
        long result= db.insert(TABLE_PRESCRIPTION,null, contentValues);
        if (result == 1)
            return false;
        else
            return true;


    }
}
