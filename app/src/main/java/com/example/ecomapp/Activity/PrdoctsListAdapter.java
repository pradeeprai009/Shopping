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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ecomapp.R;

import java.util.ArrayList;

public class PrdoctsListAdapter extends BaseAdapter {

    public Context context;
    public int layout;
    public ArrayList<Products> product_list;
    Activity activity;


    public PrdoctsListAdapter(Context context, int layout, Activity _activity, ArrayList<Products> product_list) {
        this.context = context;
        this.layout = layout;
        this.activity = _activity;
        this.product_list = product_list;
    }


    public int getCount() {
        return product_list.size();
    }

    @Override
    public Object getItem(int position) {
        return product_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView phone_image;
        TextView mobile_name, mobile_price, details_mobile;
        LinearLayout product_list;
        Button cart;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View row = view;

        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(layout, null);

            holder.mobile_name = row.findViewById(R.id.name_mobile);
            holder.mobile_price = row.findViewById(R.id.price_mobile);
            holder.details_mobile = row.findViewById(R.id.details);
            holder.phone_image = row.findViewById(R.id.phone_image);
            holder.product_list = row.findViewById(R.id.product_list);

            row.setTag(holder);
        } else {

            holder = (ViewHolder) row.getTag();
        }
        final Products products_ = product_list.get(position);
        holder.mobile_name.setText(products_.getName());
        holder.mobile_price.setText(products_.getPrice());
        holder.details_mobile.setText(products_.getDetails());

        holder.product_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_name = product_list.get(position).name;
                String price_price = product_list.get(position).price;
                String details_details = product_list.get(position).details;
                byte[] image_image = product_list.get(position).image;

                Intent intent = new Intent(activity, ProductClicked.class);
                intent.putExtra("name", name_name);
                intent.putExtra("price", price_price);
                intent.putExtra("details", details_details);
                intent.putExtra("image", image_image);
                activity.startActivity(intent);
            }
        });

        byte[] productimage = products_.getImage();

        Bitmap bitmap = BitmapFactory.decodeByteArray(productimage, 0, productimage.length);
        holder.phone_image.setImageBitmap(bitmap);
        return row;
    }
}
