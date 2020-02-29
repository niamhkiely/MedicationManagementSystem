package com.example.medicationmanagementsystem;
//code below is based on Login and Register | Sqlite Database | Android Studio | Part 1, Just Another Tutorial, https://www.youtube.com/watch?v=1WPAXHhG6u0
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

public class Register  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseHelper db;
    EditText email, password, cpassword;
    Spinner typeofuser;
    Button bRegister, bLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        db = new DatabaseHelper(this);
        email = (EditText)findViewById(R.id.txtEmailRegister);
        password = (EditText)findViewById(R.id.txtPass);
        cpassword = (EditText)findViewById(R.id.txtCPass);
        bRegister = (Button)findViewById(R.id.btnRegister);
        bLogin = (Button)findViewById(R.id.btnLogin);

        //spinner for type of user
        //the code below is based on Text Spinner- Android Studio Tutorial, Coding In Flow, https://www.youtube.com/watch?v=on_OrrX7Nw4
        typeofuser = (Spinner)findViewById(R.id.txtType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.typeofusers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeofuser.setAdapter(adapter);
        typeofuser.setOnItemSelectedListener(this);

        //END

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email.getText().toString();
                String sPassword = password.getText().toString();
                String sCPassword = cpassword.getText().toString();
                String sTypeOfUser = typeofuser.getSelectedItem().toString();


                if(sEmail.equals("")|| sPassword.equals("")|| cpassword.equals("") || sTypeOfUser.equals("") ||
                        sTypeOfUser.equals("Type of User")){
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (sPassword.equals(sCPassword)){
                        Boolean chkemail = db.chkemail(sEmail);
                        if(chkemail == true) {
                            Boolean insertUserData = db.insertUserData(sEmail, sPassword, sTypeOfUser);
                            if(insertUserData == true) {
                                Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Email Already Exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
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
            tv.setTextSize(17);
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
