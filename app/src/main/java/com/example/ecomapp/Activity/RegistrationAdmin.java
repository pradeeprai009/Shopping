package com.example.ecomapp.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.SQLiteDataBase;

public class RegistrationAdmin extends AppCompatActivity {

    EditText name,email,mobile,address,password;
    TextView register;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    String names,emails,mobiles,addresss,passwords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_admin);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        address=findViewById(R.id.address);
        password=findViewById(R.id.password);
        openHelper=new SQLiteDataBase(this);

        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid();
                db=openHelper.getReadableDatabase();
                names=name.getText().toString();
                emails=email.getText().toString();
                mobiles=mobile.getText().toString();
                addresss=address.getText().toString();
                passwords=password.getText().toString();

                insertData(names,emails,mobiles,addresss,passwords);

                Toast.makeText(RegistrationAdmin.this, "Your Registration Is Done", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(RegistrationAdmin.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public boolean valid(){
        boolean prog=true;
        String name1=name.getText().toString();
        String email1=email.getText().toString();
        String mobile1=mobile.getText().toString();
        String address1=address.getText().toString();
        String password1=password.getText().toString();

        if(name1.equals("")){
            prog=false;
            name.setError("Enter Your First name");
        }
        if(email1.equals("")){
            prog=false;
            email.setError("Enter Email");
        }
        if(mobile1.equals("")){
            prog=false;
            mobile.setError("Enter Contact No");
        }
        if(address1.equals("")){
            prog=false;
            address.setError("Enter Address");
        }
        if(password1.equals("")){
            prog=false;
            password.setError("Enter Password");
        }
        return prog;
    }
    public void insertData (String names, String emails, String mobiles, String address, String passwords){
        ContentValues contentValues =new ContentValues();
        contentValues.put(SQLiteDataBase.COL_1,names);
        contentValues.put(SQLiteDataBase.COL_2,emails);
        contentValues.put(SQLiteDataBase.COL_3,mobiles);
        contentValues.put(SQLiteDataBase.COL_4,address);
        contentValues.put(SQLiteDataBase.COL_5,passwords);
        long id=db.insert(SQLiteDataBase.TABLE_NAME,null,contentValues);
        Toast.makeText(this, " "+id,Toast.LENGTH_SHORT).show();
    }
}
