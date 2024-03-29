package com.mainpage.shuttlereservation.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.mainpage.shuttlereservation.Home;
import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.network.APIConstants;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

public class OTPVerification extends AppCompatActivity {

    private AppCompatButton btnVerify;
    private EditText otp;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        url = APIConstants.HOST + APIConstants.EMAIL_VERIFICATION;

        setupUI();
        listeners();
    }

    public void setupUI(){
        btnVerify = findViewById(R.id.verify);
        otp = findViewById(R.id.editTextOTP);
    }

    public void listeners(){
        btnVerify.setOnClickListener(view -> {
            if(otp.getText().toString()==null)
                otp.setError("Field cannot be empty");

            else {
                Intent intent = getIntent();
                boolean rememberMe = intent.getBooleanExtra("Keep Me SignedIn", false);
                String email = intent.getStringExtra("email");
                String pw = intent.getStringExtra("password");

                ShuttleResApplication.getInstance().getAppBeanFactory().getUserManager().verifyOTP(otp.getText().toString(), email,
                        new VolleyResponseListener() {
                            @Override
                            public void onError(String message) {
                                Toast.makeText(OTPVerification.this, message, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSuccess(String message) {
                                //RememberME
                                if (rememberMe)
                                    ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().rememberMe(email, pw);

                                //Go forward to HomePage
                                Intent i = new Intent(OTPVerification.this, Home.class);
                                startActivity(i);
                                Toast.makeText(OTPVerification.this, message, Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });
    }
}