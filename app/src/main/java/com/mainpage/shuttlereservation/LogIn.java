package com.mainpage.shuttlereservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity
{
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthSt;
    EditText enroll ;
    EditText passWord ;
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
//        enr2 = enroll.getText().toString() + "@bennett.edu.in";

        String reqURL = "https://shuttleres-0.herokuapp.com/api/v1/registration/login?email=" + email + "&password=" + pw;
/*
        mAuth.signInWithEmailAndPassword(enr2,pw)
                .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LogIn.this, "LogIn successful", Toast.LENGTH_SHORT)
                                    .show();
                            Intent i = new Intent(LogIn.this,Home.class);
                            startActivity(i);
                            finish();
                        }
                        else
                            Toast.makeText(LogIn.this, "Authentication unsuccessful, please try again", Toast.LENGTH_SHORT).show();
                    }
                });
*/

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
//        reqURL = String.format(reqURL, email, pw);

        // Request a string response from the provided URL.
        StringRequest request = new StringRequest(Request.Method.GET, reqURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(LogIn.this, response, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LogIn.this,Home.class);
                        startActivity(i);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                error.getMessage();
                Toast.makeText(LogIn.this, "Some Error occurred", Toast.LENGTH_LONG).show();
            }
        });

        queue.add(request);


    }

    /*public void keepLogged(View view)
    {
        checkBox = findViewById(R.id.checkBox);
        enroll = findViewById(R.id.editText);
        passWord = findViewById(R.id.editText2);

        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String enrol = enroll.getText().toString();
                String pWord = passWord.getText().toString();
                SharedPreferences shrd = getSharedPreferences("demo",MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd.edit();
                editor.putString("enroll",enrol);
                editor.putString("password",pWord);
                editor.apply();
            }
        });
        //Get the value of shared preference back
        SharedPreferences getShared = getSharedPreferences("demo",MODE_PRIVATE);
        String value1 = getShared.getString("enroll","Enter Enrollment No.");
        String value2 = getShared.getString("password","Enter Password");
        enroll.setText(value1);
        passWord.setText(value2);
        logIn(view);
    }

     */
    /*
    public void printHashKey(Context pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.d("HashKey", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("this1", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("this2", "printHashKey()", e);
        }
    }

 */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //printHashKey(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthSt = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user != null)
                {
                    Toast.makeText(LogIn.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LogIn.this,Home.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(LogIn.this, "LogIn Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthSt);
    }
}
