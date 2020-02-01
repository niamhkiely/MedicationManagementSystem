package com.example.medicationmanagementsystem;
//code below is based on Login and Register | Sqlite Database | Android Studio | Part 1, Just Another Tutorial, https://www.youtube.com/watch?v=1WPAXHhG6u0
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationmanagementsystem.DAO.DatabaseHelper;

public class Register  extends AppCompatActivity {
    DatabaseHelper db;
    EditText email, password, cpassword, typeofuser;
    Button bRegister, bLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        db = new DatabaseHelper(this);
        email = (EditText)findViewById(R.id.txtEmailRegister);
        password = (EditText)findViewById(R.id.txtPass);
        cpassword = (EditText)findViewById(R.id.txtCPass);
        typeofuser = (EditText)findViewById(R.id.txtType);
        bRegister = (Button)findViewById(R.id.btnRegister);
        bLogin = (Button)findViewById(R.id.btnLogin);

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
                String sTypeOfUser = typeofuser.getText().toString();

                if(sEmail.equals("")|| sPassword.equals("")|| cpassword.equals("") || typeofuser.equals("")){
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

}
//END
