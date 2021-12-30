package com.example.ecomapp.Activity;

import android.net.Uri;
import android.widget.LinearLayout;
import com.example.ecomapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class Products {
    public int id;
    public  String name;
    public String price;
    public String details;
    public byte[] image;

    public Products(int id, String name, String price, String details, byte [] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.image=image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
