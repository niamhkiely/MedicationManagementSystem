package com.example.medicationmanagementsystem;
//The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=8K-6gdTlGEA, https://www.youtube.com/watch?v=hHQqFGpod14, https://www.youtube.com/watch?v=jpt3Md9aDIQ
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {
    DatabaseHelper myDB;
    ArrayList<Prescription> prescriptionList;
    ListView listView;
    Prescription prescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        myDB = new DatabaseHelper(this);
        //prescriptionList = new ArrayList<>(); providing error
        prescriptionList = new ArrayList<Prescription>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(ViewListContents.this, "There is nothing in this database", Toast.LENGTH_LONG).show();
        }
        else {
            while(data.moveToNext()){
                prescription = new Prescription(data.getString(1), data.getString(2), data.getString(3),
                        data.getString(4), data.getString(5), data.getString(6), data.getString(7),
                        data.getString(8), data.getString(9));
                        prescriptionList.add(prescription);
            }
            EightColumn_ListAdapter adapter = new EightColumn_ListAdapter(this,R.layout.prescription_view,prescriptionList);
            listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);
        }
    }
}

