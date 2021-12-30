package com.example.ecomapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import com.example.ecomapp.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /** Sets a layout for this activity */
        setContentView(R.layout.splashscreen);

        /** Creates a count down timer, which will be expired after 5000 milliseconds */
        new CountDownTimer(1000,200) {

            /** This method will be invoked on finishing or expiring the timer */
            @Override
            public void onFinish() {

                /** Creates an intent to start new activity */
                Intent intent = new Intent(getApplication(), Dashboard.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }
}

