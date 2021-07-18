package com.mainpage.shuttlereservation.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity
{
    Button btnSignUp, alreadyRegistered;
    EditText email,passWord, fullName;
    AppCompatCheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupUI();
        listeners();

    }

    public void setupUI(){
        btnSignUp = findViewById(R.id.btnSignUp);
        alreadyRegistered = findViewById(R.id.lnkLogin);

        fullName = findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmail);
        passWord = findViewById(R.id.txtPwd);

        rememberMe = findViewById(R.id.rememberMe);
    }

    public void listeners(){
        btnSignUp.setOnClickListener(view -> {
            int i = 3;
            if(email.getText().toString()==null)
                email.setError("Required Field");
            else i--;
            if(passWord.getText().toString()==null)
                passWord.setError("Required Field");
            else i--;
            if(fullName.getText().toString()==null)
                fullName.setError("Required Field");
            else i--;

                if(i==0) {
                    String name = fullName.getText().toString();
                    final String[] arr = name.split(" ");
                    final String userEmail = email.getText().toString();
                    final String userPassword = passWord.getText().toString();

                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("firstName", arr[0]);
                        jsonBody.put("lastName", arr[1]);
                        jsonBody.put("email", userEmail);
                        jsonBody.put("password", userPassword);

                        ShuttleResApplication.getInstance().getAppBeanFactory().getUserManager().signUp(jsonBody,
                                new VolleyResponseListener() {
                                    @Override
                                    public void onError(String message) {
                                        Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onSuccess(String message) {
                                        Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(SignUp.this, OTPVerification.class);
                                        if (rememberMe.isChecked())
                                            i.putExtra("Keep Me SignedIn", true);
                                        i.putExtra("email", userEmail);
                                        i.putExtra("password", userPassword);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                };
        });

        alreadyRegistered.setOnClickListener(view -> {
            Intent i = new Intent(SignUp.this, LogIn.class);
            startActivity(i);
            finish();
        });
    }
}
