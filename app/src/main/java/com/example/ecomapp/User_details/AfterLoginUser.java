package com.example.ecomapp.User_details;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecomapp.Activity.ProductList;
import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.UserSQLiteDataBase;

public class AfterLoginUser extends AppCompatActivity {
    Button accountDetails,phoelist;
    String emails,name;
    String parameter;
    TextView welcomename;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_user);

        parameter = getIntent().getStringExtra("email");
        accountDetails=findViewById(R.id.accountDetails);
        welcomename=findViewById(R.id.welcomename2);
        phoelist=findViewById(R.id.phonelist);
        openHelper= new UserSQLiteDataBase(this);
        db=openHelper.getReadableDatabase();

        cursor=db.rawQuery("SELECT * FROM " + UserSQLiteDataBase.TABLE_NAME + " WHERE "
                + UserSQLiteDataBase.Email + " = ? ",new String[]{parameter});

        cursor.moveToLast();
        name=cursor.getString(cursor.getColumnIndex(UserSQLiteDataBase.Name));
        welcomename.setText(name);

        accountDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(AfterLoginUser.this, UserAccountDetails.class);

                Log.e(TAG, "onClick: "+cursor.getString(cursor.getColumnIndex(UserSQLiteDataBase.Email)));
                intent.putExtra("email",cursor.getString(cursor.getColumnIndex(UserSQLiteDataBase.Email)));
                startActivity(intent);

                }
        });
        phoelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AfterLoginUser.this, ProductList.class);
                startActivity(intent);
            }
        });
    }
}
