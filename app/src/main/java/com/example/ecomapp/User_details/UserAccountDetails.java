package com.example.ecomapp.User_details;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.UserSQLiteDataBase;

public class UserAccountDetails extends UserRegistration {
    Button back;
    TextView acname,acemail,acmobile,acaddress,acpassword;
    String parameter;
    Cursor cursor;
    String names,emails,mobiles,addresss,passwords;
    public SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_details);

        acname=findViewById(R.id.username);
        acemail=findViewById(R.id.useremail);
        acmobile=findViewById(R.id.usermobile);
        acaddress=findViewById(R.id.useraddress);
        acpassword=findViewById(R.id.userpassword);
        back=findViewById(R.id.userback);
        openHelper= new UserSQLiteDataBase(this);
        db=openHelper.getReadableDatabase();
        parameter = getIntent().getStringExtra("email");

        cursor=db.rawQuery("SELECT * FROM " + UserSQLiteDataBase.TABLE_NAME + " WHERE "
                + UserSQLiteDataBase.Email + " = ? ", new String[]{parameter});

        cursor.moveToLast();
        names=cursor.getString(cursor.getColumnIndex(UserSQLiteDataBase.Name));
        acname.setText(names);
        emails=cursor.getString(cursor.getColumnIndex(UserSQLiteDataBase.Email));
        acemail.setText(emails);
        mobiles=cursor.getString(cursor.getColumnIndex(UserSQLiteDataBase.Mobile));
        acmobile.setText(mobiles);
        addresss=cursor.getString(cursor.getColumnIndex(UserSQLiteDataBase.Address));
        acaddress.setText(addresss);
        passwords=cursor.getString(cursor.getColumnIndex(UserSQLiteDataBase.Pass));
        acpassword.setText(passwords);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}