package com.mainpage.shuttlereservation.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mainpage.shuttlereservation.Home;
import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.network.APIConstants;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

import org.json.JSONObject;

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
            ShuttleResApplication.getInstance().getAppBeanFactory().getUserManager().verifyOTP(otp.getText().toString(),
                    new VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(OTPVerification.this, message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(String message) {
                            Intent intent = getIntent();
                            ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUserDetails(intent.
                                    getStringExtra("email"));
                            if(intent.getBooleanExtra("Keep Me SignedIn", false))
                                ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().rememberMe(intent.getStringExtra("email"),
                                        intent.getStringExtra("password"));
                            Intent i = new Intent(OTPVerification.this, Home.class);
                            startActivity(i);
                            Toast.makeText(OTPVerification.this, message, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
        });
    }
}