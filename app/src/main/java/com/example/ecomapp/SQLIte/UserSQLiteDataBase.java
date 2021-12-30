package com.example.ecomapp.SQLIte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserSQLiteDataBase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME="UserRe";
    public static final String TABLE_NAME="User";
    public static final String Name="name";
    public static final String Email="email";
    public static final String Mobile="mobile";
    public static final String Address="address";
    public static final String Pass="password";

    public UserSQLiteDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase2) {
        String CREATE_PROJECT =" CREATE TABLE " + TABLE_NAME + "(" + Name + " TEXT, " + Email + " TEXT, "
                + Mobile + " TEXT, " + Address + " TEXT, " + Pass + " TEXT " + ")";
        sqLiteDatabase2.execSQL(CREATE_PROJECT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
