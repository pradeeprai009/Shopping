package com.example.ecomapp.SQLIte;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ecomapp.Activity.Carts;

import java.sql.Blob;
import java.util.ArrayList;

public class SQLiteDataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME ="products";
    public static final String TABLE_NAME ="admin";
    public static final String COL_1="name";
    public static final String COL_2="email";
    public static final String COL_3="mobile";
    public static final String COL_4="address";
    public static final String COL_5="password";

    public static final String TABLE_NAME2 ="cart";
    public static final String  NAME="name";
    public static final String PRICE="price";
    public static final String DETAILS="details";
    public static final String IMAGES="images";


    String CREATE_PROJECT ="CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " TEXT," + COL_2 + " TEXT,"
            + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT" + ")";

     String CREATE_CART ="CREATE TABLE " + TABLE_NAME2 + "(" + NAME + " TEXT," + PRICE + " TEXT,"
            + DETAILS + " TEXT," + IMAGES + " BLOB" + ")";

    public SQLiteDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1 );
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_PROJECT);
        sqLiteDatabase.execSQL(CREATE_CART);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }
    public ArrayList<Carts> getalldata() {
        ArrayList<Carts> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);

        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
            String name = cursor.getString(0);
            String price = cursor.getString(1);
            String details = cursor.getString(2);
            byte [] image = cursor.getBlob(3);
            Carts carts = new Carts(0, name, price, details,image);
            arrayList.add(carts);
        }
        return arrayList;
    }
}

