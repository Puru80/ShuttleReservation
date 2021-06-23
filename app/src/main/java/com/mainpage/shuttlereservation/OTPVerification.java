package com.mainpage.shuttlereservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
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
        btnVerify.setOnClickListener(l -> {
            url = APIConstants.HOST + APIConstants.EMAIL_VERIFICATION;
            final int[] responseStatus = new int[1];
            try{
                RequestQueue queue = Volley.newRequestQueue(this);

                url += otp.getText().toString();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                        null, response -> {
//                    ShuttleResApplication.getInstance().getAppBeanFactory().getUser().setFirstName("Puru");
//                    ShuttleResApplication.getInstance().getAppBeanFactory().getUser().setLastName("Agarwal");
//                    ShuttleResApplication.getInstance().getAppBeanFactory().getUser().setUserEmail("Puru");

                        Toast.makeText(this, "Email Confirmed", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this, Home.class);
                        startActivity(i);
                        }, error -> {
                    NetworkResponse networkResponse = error.networkResponse;
                    try{
                        if(networkResponse!=null && networkResponse.data!=null){
                            String jsonError = new String(networkResponse.data);
                            JSONObject object = new JSONObject(jsonError);
                            String er = object.getJSONObject("data").getString("message");
                            Toast.makeText(this, er, Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                queue.add(jsonObjectRequest);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}