package com.example.ecomapp.User_details;

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

import com.example.ecomapp.ForgetPassword.ForgetPassword;
import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.SQLiteDataBase;
import com.example.ecomapp.SQLIte.UserSQLiteDataBase;

public class UserForgetPassword extends AppCompatActivity {
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
        setContentView(R.layout.activity_user_forget_password);

        fogemail = findViewById(R.id.user_forgetemail);
        getpasshere = findViewById(R.id.user_showpass); //show pass
        showpass = findViewById(R.id.showpasstouser);
        openHelper = new UserSQLiteDataBase(this);
        db = openHelper.getReadableDatabase();
        parameter = getIntent().getStringExtra("email");

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = fogemail.getText().toString();

                cursor = db.rawQuery("SELECT * FROM " + UserSQLiteDataBase.TABLE_NAME + " WHERE "
                        + UserSQLiteDataBase.Email + " = ? ", new String[]{email});

                if (cursor != null) {

                    if (cursor.getCount() > 0) {

                        cursor.moveToNext();

                        pass = cursor.getString(cursor.getColumnIndex(UserSQLiteDataBase.Pass));
                        getpasshere.setText(pass);

                    }else {

                        Toast.makeText(UserForgetPassword.this, "Your Details Are Wrong", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
