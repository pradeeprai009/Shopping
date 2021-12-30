package com.example.ecomapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.SQLiteDataBase;

import java.util.ArrayList;

public class CartList extends AppCompatActivity {

    SQLiteDataBase db;
    ListView listView3;
    ArrayList<Carts> list2;
    CartAdapter adapter;
    Button movenext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_containts);
        movenext=findViewById(R.id.movenext);

        movenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartList.this, "Your Oder Has Been Placed, Thank Your For Shopping with Us", Toast.LENGTH_LONG).show();
            }
        });

        listView3=findViewById(R.id.list3);
        list2=new ArrayList();
        listView3.setAdapter(adapter);

        db=new SQLiteDataBase(this);
        loadDataInListView();

    }

    private void loadDataInListView() {
        list2=db.getalldata();
        adapter=new CartAdapter(this,list2);
        adapter.notifyDataSetChanged();
        listView3.setAdapter(adapter);
    }
}
