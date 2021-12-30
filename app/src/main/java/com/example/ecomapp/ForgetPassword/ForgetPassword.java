package com.example.ecomapp.ForgetPassword;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecomapp.Activity.RegistrationAdmin;
import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.SQLiteDataBase;
import com.example.ecomapp.SQLIte.UserSQLiteDataBase;

public class ForgetPassword extends AppCompatActivity {

    EditText fogemail;
    Button showpass;
    TextView getpasshere;
    String parameter;
    String pass;
    private SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        fogemail = findViewById(R.id.fogemail);
        getpasshere = findViewById(R.id.getpasshere); //show pass
        showpass = findViewById(R.id.showpass);
        openHelper = new SQLiteDataBase(this);
        db = openHelper.getReadableDatabase();
        parameter = getIntent().getStringExtra("phone");

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = fogemail.getText().toString();

                cursor = db.rawQuery(" SELECT * FROM " + SQLiteDataBase.TABLE_NAME + " WHERE "
                        + SQLiteDataBase.COL_2 + " = ? ", new String[]{email});

                if (cursor != null) {
                    if (cursor.getCount() > 0) {

                        cursor.moveToNext();
                        pass = cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_5));
                        getpasshere.setText(pass);
                    }else {
                        Toast.makeText(ForgetPassword.this, "Your Details Are Wrong", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}