package com.mainpage.shuttlereservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.mainpage.shuttlereservation.network.APIConstants;

import org.json.JSONObject;

public class SignUp extends AppCompatActivity
{
    FirebaseAuth mAuth;
    Button btnSignUp, alreadyRegistered;
    EditText email,passWord, fullName;
    public final String TAG = "Registration Volley";

    public void signUp()
    {
        fullName = findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmail);
        passWord = findViewById(R.id.txtPwd);

        String name = fullName.getText().toString();
        final String[] arr = name.split(" ");
        final String userEmail = email.getText().toString();
        final String userPassword = passWord.getText().toString();

        final String URL = APIConstants.HOST + APIConstants.SIGN_UP;

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("firstName", arr[0]);
            jsonBody.put("lastName", arr[1]);
            jsonBody.put("email", userEmail);
            jsonBody.put("password", userPassword);
            final String mRequestBody = jsonBody.toString();

            System.out.println(mRequestBody);

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL, jsonBody,
                    response -> {
                        Log.d(TAG, "onResponse: " + response.toString());
                        Intent i = new Intent(this, OTPVerification.class);
                        startActivity(i);
                        finish();
                    },
                    error -> {
                        try {
                            NetworkResponse networkResponse = error.networkResponse;
                            System.out.println(error.toString());
                            if (networkResponse != null && networkResponse.data != null) {
                                String jsonError = new String(networkResponse.data);
                                JSONObject object = new JSONObject(jsonError);
                                String er = object.getJSONObject("data").getString("message");
                                Toast.makeText(this, er, Toast.LENGTH_SHORT).show();
                            }}catch (Exception e){
                                Toast.makeText(this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                        }
            });

            requestQueue.add(req);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        btnSignUp = findViewById(R.id.btnSignUp);
        alreadyRegistered = findViewById(R.id.lnkLogin);

        btnSignUp.setOnClickListener(view -> {
            Log.d(TAG, "onClick: Entered in btnSignUp onClick");
            signUp();
        });

        alreadyRegistered.setOnClickListener(view -> {
            Intent i = new Intent(SignUp.this, LogIn.class);
            startActivity(i);
            finish();
        });

    }
}
