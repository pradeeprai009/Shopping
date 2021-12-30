package com.example.ecomapp.Activity;

import android.support.v7.app.AppCompatActivity;

public class Carts extends AppCompatActivity {

    int id;
    String name, price, details;
    public byte[] image;

    public Carts(int id, String name, String price, String details, byte[] image) {

        this.id=id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.image = image;
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

    public byte[] get_Image() {
        return image;
    }

    public void set_Image(byte[] image) {
        this.image = image;
    }
}
