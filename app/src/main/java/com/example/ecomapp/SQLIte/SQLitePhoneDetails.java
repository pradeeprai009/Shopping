package com.example.ecomapp.SQLIte;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.ContactsContract;

public class SQLitePhoneDetails extends SQLiteOpenHelper {

    public SQLitePhoneDetails(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, String price, String details, byte []images){
        SQLiteDatabase database = getWritableDatabase();
        String sql =" INSERT INTO PHONE VALUES (NULL, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindString(3,details);
        statement.bindBlob(4,images);
        statement.executeInsert();
    }
    public int CountData(){
        int dataCount =0;
        String sql ="SELECT COUNT(*) FROM PHONE";
        Cursor cursor =getReadableDatabase().rawQuery(sql,null);
       if(cursor.getCount()>0){
           cursor.moveToFirst();
           dataCount=cursor.getInt(0);
       }


        return dataCount;
    }
    public void updateData (String name, String price, String details, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE PHONE SET name = ?, price = ?, details = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(0, name);
        statement.bindString(1, price);
        statement.bindString(2,details);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM PHONE WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}