package com.example.ecomapp.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.ecomapp.Navigatore_Activity.AddProducts;
import com.example.ecomapp.R;

import java.util.ArrayList;

public class ProductList extends AddProducts {

    GridView listView;
    ArrayList<Products> list;
    PrdoctsListAdapter adapter = null;
    Cursor cursor;
    Button Cart;
    Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        listView =findViewById(R.id.ListView);
        list = new ArrayList<>();
        activity=this;

        adapter = new PrdoctsListAdapter(this, R.layout.products_items,  activity, list);
        listView.setAdapter(adapter);

        Cart=findViewById(R.id.Cart);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte= new Intent(ProductList.this, CartList.class);
                startActivity(inte);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent =new Intent(ProductList.this,ProductClicked.class);
                startActivity(intent);
            }
        });
        cursor = AddProducts.sqLitePhoneDetails.getData("SELECT * FROM PHONE");
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String details = cursor.getString(3);
            byte [] image = cursor.getBlob(4);

            list.add(new Products(id, name, price, details, image));
        }
        adapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                android.support.v7.app.AlertDialog.Builder dialog = new AlertDialog.Builder(ProductList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = AddProducts.sqLitePhoneDetails.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(ProductList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = AddProducts.sqLitePhoneDetails.getData("SELECT id FROM PHONE");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    private void showDialogDelete(final int idPhone){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ProductList.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    AddProducts.sqLitePhoneDetails.deleteData(idPhone);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }
    private void updateFoodList(){
        // get all data from sqlite
        Cursor cursor = AddProducts.sqLitePhoneDetails.getData("SELECT * FROM PHONE");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String details=cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            list.add(new Products( id, name, price, details, image));
        }
        adapter.notifyDataSetChanged();
    }
    ImageView imageViewFood;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_food_activity);
        dialog.setTitle("Update");

        imageViewFood = (ImageView) dialog.findViewById(R.id.imageViewFood);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        final EditText edtdetais = (EditText) dialog.findViewById(R.id.editdetails);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        ProductList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AddProducts.sqLitePhoneDetails.updateData(
                            edtName.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            edtdetais.getText().toString().trim(),
                            AddProducts.imageViewToByte(imageViewFood),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateFoodList();
            }
        });
    }

}
