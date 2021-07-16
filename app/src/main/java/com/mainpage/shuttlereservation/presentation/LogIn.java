package com.mainpage.shuttlereservation.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mainpage.shuttlereservation.Home;
import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

public class LogIn extends AppCompatActivity
{
    EditText enroll , passWord;
    Button btnLogIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setupUI();
        listeners();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    public void setupUI(){
        enroll = findViewById(R.id.editText);
        passWord = findViewById(R.id.editText2);

        btnLogIn = findViewById(R.id.btLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    public void listeners(){
        btnLogIn.setOnClickListener(v ->{
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
                            ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUserDetails(email);
                            Toast.makeText(LogIn.this, message, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(LogIn.this, Home.class);
                            startActivity(i);
                            finish();
                        }
                    });
        });

        btnSignUp.setOnClickListener(v ->{
            Intent intent = new Intent(LogIn.this,SignUp.class);
            startActivity(intent);
            finish();
        });
    }
}
