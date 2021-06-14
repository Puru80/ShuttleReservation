package com.mainpage.shuttlereservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.net.URI;

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

        String reqURL = APIConstants.HOST + APIConstants.SIGN_IN + email +
                "&password=" + pw;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, reqURL, null,
                    response -> {
                        Toast.makeText(LogIn.this, "SignIn Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LogIn.this,Home.class);
                        startActivity(i);
                        finish();
                    }, error -> Toast.makeText(LogIn.this, "Some Error occured", Toast.LENGTH_SHORT).show());

            requestQueue.add(request1);
        }catch (Exception e){
            e.printStackTrace();
        }


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
