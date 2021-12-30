package com.example.ecomapp.User_details;

import android.content.ContentValues;
import android.content.Intent;
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

public class UserRegistration extends AppCompatActivity {

    EditText name,email,mobile,address,password;
    TextView register;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
   String names,emails,mobiles,addresss,passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__registration);

        name=findViewById(R.id.user_namereg);
        email=findViewById(R.id.user_emailreg);
        mobile=findViewById(R.id.user_mobilereg);
        address=findViewById(R.id.user_addressreg);
        password=findViewById(R.id.user_passwordreg);
        openHelper=new UserSQLiteDataBase(this);
        register=findViewById(R.id.user_registerreg);

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

                Toast.makeText(UserRegistration.this, " Your Registration Is Done ", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(UserRegistration.this, UserLogin.class);
                startActivity(intent);
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
    public void insertData (String name, String email, String mobile, String addres, String password){
        ContentValues contentValue =new ContentValues();
        contentValue.put(UserSQLiteDataBase.Name,name);
        contentValue.put(UserSQLiteDataBase.Email,email);
        contentValue.put(UserSQLiteDataBase.Mobile,mobile);
        contentValue.put(UserSQLiteDataBase.Address,addres);
        contentValue.put(UserSQLiteDataBase.Pass,password);
        long id=db.insert(UserSQLiteDataBase.TABLE_NAME,null,contentValue);

        Toast.makeText(this, "Done "+id,Toast.LENGTH_SHORT).show();
    }
}
