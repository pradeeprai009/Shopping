package com.example.ecomapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecomapp.R;

import java.util.ArrayList;

public class ProductClickedAdapter extends BaseAdapter {

    private Context context;
    private int layout2;
    private ArrayList<Products> product_list_clicked;
    Activity activity;

    public ProductClickedAdapter (Context context,Activity _activity, int layout2, ArrayList<Products> product_list_clicked) {
        this.context = context;
        this.layout2 = layout2;
        this.activity = _activity;
        this.product_list_clicked = product_list_clicked;
    }

    @Override
    public int getCount() {
        return product_list_clicked.size();
    }

    @Override
    public Object getItem(int position) {
        return product_list_clicked.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView phone_image;
        TextView mobile_name, mobile_price, details_mobile;
        Button adapter_buynow;
    }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {

            View row = view;
            ViewHolder holder=new ViewHolder();

            if(row==null){
                LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row=layoutInflater.inflate(layout2,null);

                holder.mobile_name =(TextView) row.findViewById(R.id.buy_name_mobile);
                holder.mobile_price=(TextView) row.findViewById(R.id.buy_price_mobile);
                holder.details_mobile=(TextView) row.findViewById(R.id.buy_details);
                holder.phone_image=(ImageView)row.findViewById(R.id.buy_phone_image);
                holder.adapter_buynow=row.findViewById(R.id.adapter_buynow);

                row.setTag(holder);
            }
            else{

                holder =(ViewHolder)row.getTag();
            }
            final Products products_ =product_list_clicked.get(position);
            holder.mobile_name.setText(products_.getName());
            holder.mobile_price.setText(products_.getPrice());
            holder.details_mobile.setText(products_.getDetails());

            byte[] productimage = products_.getImage();

            Bitmap bitmap= BitmapFactory.decodeByteArray(productimage,0,productimage.length);
            holder.phone_image.setImageBitmap(bitmap);

            return row;
        }


}
