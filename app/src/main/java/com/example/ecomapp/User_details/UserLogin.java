package com.example.ecomapp.User_details;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.UserSQLiteDataBase;

public class UserLogin extends AppCompatActivity {
    TextView forgetpassword,login,createid;
    EditText email,password;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    String parameter2;
    String emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);

        forgetpassword=findViewById(R.id.userforgetpass);
        login=findViewById(R.id.loginuser);
        createid=findViewById(R.id.usercreateid);
        email=findViewById(R.id.emailuser);
        password=findViewById(R.id.passworduser);
        openHelper=new UserSQLiteDataBase(this);
        db=openHelper.getReadableDatabase();
        parameter2=getIntent().getStringExtra("email");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  emails=email.getText().toString();
                String pass=password.getText().toString();

                cursor=db.rawQuery(" SELECT * FROM " + UserSQLiteDataBase.TABLE_NAME + " WHERE " + UserSQLiteDataBase.Email
                        + " = ? AND " + UserSQLiteDataBase.Pass + " = ? ",new String[] {emails,pass});

                if(cursor !=null){

                    if(cursor.getCount() > 0)
                    {
                        cursor.moveToNext();
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(UserLogin.this, AfterLoginUser.class);
                        intent.putExtra("email",emails);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Wrong Mobile or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLogin.this,UserForgetPassword.class);
                intent.putExtra("email",emails);
                startActivity(intent);
            }
        });

        createid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLogin.this, UserRegistration.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
