package com.example.ecomapp.Activity;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecomapp.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Carts> arrayList;

    public  CartAdapter(Context context, ArrayList<Carts> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mycustomelistview, null);
            TextView cart_name = convertView.findViewById(R.id.add_name);
            TextView cart_price = convertView.findViewById(R.id.add_price);
            TextView cart_details = convertView.findViewById(R.id.add_details);
            ImageView cart_image =convertView.findViewById(R.id.add_image);

            Carts carts = arrayList.get(position);
            cart_name.setText(carts.getName());
            cart_price.setText(carts.getPrice());
            cart_details.setText(carts.getDetails());

            Log.e("test", "getView: " +carts.get_Image());

            byte [] Image = carts.get_Image();
            Bitmap bitmap= BitmapFactory.decodeByteArray(Image,0,Image.length);
            cart_image.setImageBitmap(bitmap);

            return convertView;
    }
}