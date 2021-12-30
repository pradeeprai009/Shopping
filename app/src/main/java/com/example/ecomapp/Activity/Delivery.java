package com.example.ecomapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecomapp.R;

public class Delivery extends AppCompatActivity {
    EditText full_address;
    ImageView dil_phone_image;
    TextView dil_price, amount_payable, dil_name_mobile, dil_price_mobile, dil_buy_details;
    String name,price,details;
    Button place_order,dil_continue;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle oldInstanceState) {
        super.onCreate(oldInstanceState);
        setContentView(R.layout.activity_delivery);

        //Getting the Data From ProductClicked through Bundle.
        //Catching the Data from its Id "names"

        Bundle extras = getIntent().getExtras();
        name= extras.getString("name");
        price= extras.getString("price");
        details= extras.getString("details");
        byte[] img = getIntent().getByteArrayExtra("image");
        bitmap= BitmapFactory.decodeByteArray(img,0,img.length);

        full_address=findViewById(R.id.full_address);
        dil_phone_image=findViewById(R.id.dil_phone_image);
        dil_price=findViewById(R.id.dil_price);
        amount_payable=findViewById(R.id.amount_payable);
        dil_name_mobile=findViewById(R.id.dil_name_mobile);
        dil_price_mobile=findViewById(R.id.dil_price_mobile);
        dil_buy_details=findViewById(R.id.dil_buy_details);
        place_order=findViewById(R.id.place_order);
        dil_continue=findViewById(R.id.dil_continue);

        //Setting the Data to its Ids
        dil_name_mobile.setText(name);
        dil_price_mobile.setText(price);
        dil_buy_details.setText(details);
        dil_phone_image.setImageBitmap(bitmap);
        amount_payable.setText(price);
        dil_price.setText(price);

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Delivery.this, "Your Order Has Been Place, Thank You For Shopping with Us", Toast.LENGTH_LONG).show();
                Intent in =new Intent(Delivery.this,ProductList.class);
                startActivity(in);
                finish();
            }
        });
        dil_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Delivery.this,ProductList.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
