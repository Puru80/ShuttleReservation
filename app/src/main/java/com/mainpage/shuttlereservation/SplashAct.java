package com.mainpage.shuttlereservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashAct extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashact);

        long splash_Timeout = 4000;
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i = new Intent(SplashAct.this,LogIn.class);
                startActivity(i);
                finish();
            }
        }, splash_Timeout);

    }
}
