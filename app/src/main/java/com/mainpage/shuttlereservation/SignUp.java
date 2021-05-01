package com.mainpage.shuttlereservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity
{
    FirebaseAuth mAuth;
    EditText enroll,passWord;

    public void signUp(View view)
    {
        enroll = findViewById(R.id.editText);
        passWord = findViewById(R.id.editText2);

        String enr = enroll.getText().toString() + "@bennett.edu.in";
        String pw = passWord.getText().toString();

        mAuth.createUserWithEmailAndPassword(enr,pw)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUp.this, "SignUp successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUp.this, Home.class);
                            startActivity(i);
                            finish();
                        } else
                        {
                            Toast.makeText(SignUp.this, "SignUp unsuccessful, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

    }
}
