package com.mainpage.shuttlereservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mainpage.shuttlereservation.network.VolleyResponseListener;

public class LogIn extends AppCompatActivity
{
    EditText enroll ;
    EditText passWord ;
    public final String TAG = "LogIn Volley";
//    String email;

    public void signUp(View view)
    {
        Intent intent = new Intent(LogIn.this,SignUp.class);
        startActivity(intent);
        finish();
    }

    public void logIn(View view)
    {
        enroll = findViewById(R.id.editText);
        passWord = findViewById(R.id.editText2);

        String pw = passWord.getText().toString();
        String email = enroll.getText().toString();


        ShuttleResApplication.getInstance().getAppBeanFactory().getUserManager().signIn(email, pw,
                new VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(LogIn.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(LogIn.this, message, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LogIn.this,Home.class);
                        startActivity(i);
                        finish();
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //printHashKey(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }
}
