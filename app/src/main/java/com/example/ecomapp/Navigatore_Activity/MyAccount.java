package com.example.ecomapp.Navigatore_Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.ecomapp.Activity.RegistrationAdmin;
import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.SQLiteDataBase;

public class MyAccount extends RegistrationAdmin {
    Button back;
    TextView acname,acemail,acmobile,acaddress,acpassword;
    String parameter2;
    private Cursor cursor;
    String names,emails,mobiles,addresss,passwords;
    public SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__account);

        acname=findViewById(R.id.acname);
        acemail=findViewById(R.id.acemail);
        acmobile=findViewById(R.id.acmobile);
        acaddress=findViewById(R.id.acaddress);
        acpassword=findViewById(R.id.acpassword);
        back=findViewById(R.id.back);
        openHelper= new SQLiteDataBase(this);
        db=openHelper.getReadableDatabase();

        parameter2=getIntent().getStringExtra("phone");

        cursor=db.rawQuery("SELECT * FROM " + SQLiteDataBase.TABLE_NAME + " WHERE "
                + SQLiteDataBase.COL_3 + " = ? ",new String[]{parameter2});

        cursor.moveToLast();
        names=cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_1));
        acname.setText(names);
        emails=cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_2));
        acemail.setText(emails);
        mobiles=cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_3));
        acmobile.setText(mobiles);
        addresss=cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_4));
        acaddress.setText(addresss);
        passwords=cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_5));
        acpassword.setText(passwords);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();

            }
        });
    }
}
