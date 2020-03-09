package com.example.medicationmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

//code below is based on Navigation Drawer with Fragments Part 2- Layout and Hamburger Icon, Coding In Flow, https://www.youtube.com/watch?v=zYVEMCiDcmY
public class AdminHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout admindrawer;
    TextView txtDisplayEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nurse_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        admindrawer = findViewById(R.id.nursedrawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        txtDisplayEmail = (TextView) header.findViewById(R.id.emaildisplay);
        //code below is based on Send user Name to display another Activity in android Studio, sabsab Chanel, https://www.youtube.com/watch?v=SN5cgAU0vYo
        Intent adminintent = getIntent();
        String email = adminintent.getStringExtra("EMAIL");
        txtDisplayEmail.setText(email);
        //END
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, admindrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        admindrawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    //END
    //code below is based on Navigation Drawer with Fragments Part 3- Handling menu items clicks, Coding In Flow, https://www.youtube.com/watch?v=bjYstsO1PgI
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_nurseviewprescription:
                Intent presintent = new Intent(AdminHome.this, ViewListContents.class);
                startActivity(presintent);
                break;
            case R.id.nav_nurseviewpatients:
                Intent viewpatientintent = new Intent(AdminHome.this, AdminViewPatients.class);
                startActivity(viewpatientintent);
                break;
            case R.id.nav_viewcaringlist:
                Intent viewcaringlist= new Intent(AdminHome.this, ViewCaringList.class);
                startActivity(viewcaringlist);
                break;
            case R.id.nav_logout:
                Intent logoutintent= new Intent(AdminHome.this, Login.class);
                startActivity(logoutintent);
                break;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        if (admindrawer.isDrawerOpen(GravityCompat.START)) {
            admindrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
//END
