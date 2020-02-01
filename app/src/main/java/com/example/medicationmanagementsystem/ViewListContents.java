package com.example.medicationmanagementsystem;
//The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=hHQqFGpod14
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {
  DatabaseHelper myDB;
  ArrayList<Prescription> prescriptionList;
  ListView listView;
  Prescription prescription;
  ArrayAdapter adapter;
  ArrayList<String> listItem = new ArrayList<String>();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    //setting layout
    setContentView(R.layout.viewcontents_layout);

    myDB = new DatabaseHelper(this);
    listItem = new ArrayList<String>();
    //create new array
    prescriptionList = new ArrayList<Prescription>();
    Cursor data = myDB.getListContents();
    int numRows = data.getCount();
    //if there is no rows in the database
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
  //END

  //This code is based on SQLite Database to ListView- Part 4:Search Items- Android Studio Tutorial, KOD Dev, https://www.youtube.com/watch?v=QY-O49a_Ags
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);

    MenuItem searchItem = menu.findItem(R.id.item_search);
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {

        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        ArrayList<String> listViews = new ArrayList<>();
        for (String prescription : listItem){
          if (prescription.toLowerCase().contains(newText.toLowerCase())){
            listViews.add(prescription);
          }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewListContents.this,R.layout.prescription_view,listViews);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        return true;
      }
    });
    return super.onCreateOptionsMenu(menu);
  }
  }
  //END
