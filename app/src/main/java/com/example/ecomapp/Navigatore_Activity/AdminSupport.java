package com.example.ecomapp.Navigatore_Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.ecomapp.Activity.Login;
import com.example.ecomapp.Activity.ProductList;
import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.SQLiteDataBase;

public class AdminSupport extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imagegif;
    TextView welcomename;
    String name;
    String details;
    String parameter;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__support);
        parameter=getIntent().getStringExtra("phone");

        imagegif=findViewById(R.id.imggif);
        welcomename=findViewById(R.id.welcomename);
        openHelper=new SQLiteDataBase(this);
        db=openHelper.getReadableDatabase();

        cursor=db.rawQuery("SELECT * FROM " + SQLiteDataBase.TABLE_NAME + " WHERE "
                + SQLiteDataBase.COL_3 + " = ? ",new String[]{parameter});

        cursor.moveToLast();
        name=cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_1));
        welcomename.setText(name);

        String gifURL ="http://driveinstyle.lk/img/img_gif/no_result_found.gif";
        String gifURL2 ="https://i.pinimg.com/originals/9a/ac/34/9aac3469b8fb8a8920d0cfb61c44256c.gif";
        Glide .with(this)
                .load(gifURL)
                .into(imagegif);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin__support, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "You Have been Logout", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AdminSupport.this, Login.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.myaccount) {
            Intent intent =new Intent(AdminSupport.this, MyAccount.class);
            intent.putExtra("phone",cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_3)));
            startActivity(intent);

        } else if (id == R.id.addproducts) {
            Intent intent =new Intent(AdminSupport.this, AddProducts.class);
            startActivity(intent);


        } else if (id == R.id.showproducts) {
            Intent intent=new Intent(AdminSupport.this, ProductList.class);
            intent.putExtra("details",details);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
