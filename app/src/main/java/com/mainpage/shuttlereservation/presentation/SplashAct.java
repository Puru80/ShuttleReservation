package com.mainpage.shuttlereservation.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.mainpage.shuttlereservation.Home;
import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

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
                checkForUser();

                Intent i = new Intent(SplashAct.this,LogIn.class);
                startActivity(i);
                finish();
            }
        }, splash_Timeout);

    }

    public void checkForUser(){
        SharedPreferences sharedPreferences = ShuttleResApplication.getCtx().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "None");
        String pw = sharedPreferences.getString("password", "None");

        if(!email.equals("None")) {
            logIn(email, pw);
        }
    }

    public void logIn(String email ,String pw){
        ShuttleResApplication.getInstance().getAppBeanFactory().getUserManager().signIn(email, pw,
                new VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(SplashAct.this, message, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SplashAct.this, LogIn.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onSuccess(String message) {
                        ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUserDetails(email);
                        Toast.makeText(SplashAct.this, message, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SplashAct.this, Home.class);
                        startActivity(i);
                        finish();
                    }
                });
    }
}
