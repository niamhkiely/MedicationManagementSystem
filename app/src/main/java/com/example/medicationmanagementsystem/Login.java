package com.example.medicationmanagementsystem;
//code below is based on Login and Register | Sqlite Database | Android Studio | Part 1, Just Another Tutorial, https://www.youtube.com/watch?v=1WPAXHhG6u0
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

public class Login extends AppCompatActivity {
    EditText loginemail, loginpassword;
    Button btLogin, btRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        db = new DatabaseHelper(this);
        loginemail = (EditText)findViewById(R.id.txtEmail);
        loginpassword = (EditText)findViewById(R.id.txtPassword);
        btLogin = (Button)findViewById(R.id.ButtonLogin);
        btRegister = (Button)findViewById(R.id.ButtonRegister);

        btRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = loginemail.getText().toString();
                String password = loginpassword.getText().toString();
                Boolean Chkemailpass = db.emailpassword(email, password);
                if(Chkemailpass == true) {
                    String typeofuser;
                    typeofuser = db.checkUser(loginemail.getText().toString()); //get user type from database
                    if (typeofuser.equals("Nurse")) {
                        Intent mainintent = new Intent(Login.this, NurseHome.class);
                        //code below is based on Send user Name to display another Activity in android Studio, sabsab Chanel, https://www.youtube.com/watch?v=SN5cgAU0vYo
                        mainintent.putExtra("EMAIL", email);
                        //END
                        startActivity(mainintent);
                    } else if (typeofuser.equals("Doctor")) {
                        Intent intent = new Intent(Login.this, DoctorHome.class);
                        //code below is based on Send user Name to display another Activity in android Studio, sabsab Chanel, https://www.youtube.com/watch?v=SN5cgAU0vYo
                        intent.putExtra("EMAIL", email);
                        //END
                        startActivity(intent);
                    } else if (typeofuser.equals("Admin")){
                        Intent adminintent = new Intent(Login.this, AdminHome.class);
                        //code below is based on Send user Name to display another Activity in android Studio, sabsab Chanel, https://www.youtube.com/watch?v=SN5cgAU0vYo
                        adminintent.putExtra("EMAIL",email);
                        startActivity(adminintent);}
                }
                    //Toast.makeText(getApplicationContext(),"Successfully Login", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Wrong email or password", Toast.LENGTH_SHORT).show();
                }
        });

        }
}
//END