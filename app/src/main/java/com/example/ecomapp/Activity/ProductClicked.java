package com.example.ecomapp.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.SQLiteDataBase;

public class ProductClicked extends AppCompatActivity {

    ImageView buy_phone_image;
    TextView buy_name_mobile,buy_price_mobile,buy_details;
    Button addtocart,adapter_buynow;
    String nam,pr,det;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Bitmap bmp;
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle oldInstanceState) {
        super.onCreate(oldInstanceState);
        setContentView(R.layout.productclicked_items);
        openHelper=new SQLiteDataBase(this);

        Bundle extras = getIntent().getExtras();
        nam= extras.getString("name");
        pr= extras.getString("price");
        det= extras.getString("details");
        byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        buy_name_mobile=findViewById(R.id.buy_name_mobile);
        buy_phone_image=findViewById(R.id.buy_phone_image);
        buy_price_mobile=findViewById(R.id.buy_price_mobile);
        buy_details=findViewById(R.id.buy_details);
        addtocart=findViewById(R.id.addtocart);
        adapter_buynow=findViewById(R.id.adapter_buynow);

        //Setting Data to its Ids
        buy_name_mobile.setText(nam);
        buy_price_mobile.setText(pr);
        buy_details.setText(det);
        buy_phone_image.setImageBitmap(bmp);


        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db=openHelper.getWritableDatabase();

                insertData(nam, pr, det, byteArray);

                Toast.makeText(ProductClicked.this, " Item Added to Cart ", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(ProductClicked.this, CartList.class);
                //Sending the Data through Intent
//                intent.putExtra("name",nam);
//                intent.putExtra("price",pr);
//                intent.putExtra("details",det);
//                intent.putExtra("image",byteArray);
                startActivity(intent);
                finish();
            }
        });

        adapter_buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(ProductClicked.this, Delivery.class);
                //Sending the Data through Intent
                intent.putExtra("name",nam);
                intent.putExtra("price",pr);
                intent.putExtra("details",det);
                intent.putExtra("image",byteArray);
                startActivity(intent);
                finish();
            }
        });
    }

    public void insertData(String cart_name, String cart_price, String cart_details, byte[] images) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDataBase.NAME, cart_name);
        contentValues.put(SQLiteDataBase.PRICE, cart_price);
        contentValues.put(SQLiteDataBase.DETAILS, cart_details);
        contentValues.put(SQLiteDataBase.IMAGES, images);
        long id=db.insert(SQLiteDataBase.TABLE_NAME2,null,contentValues);
        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
    }
}
