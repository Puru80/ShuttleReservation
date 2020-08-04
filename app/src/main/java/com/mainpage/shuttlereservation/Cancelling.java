package com.mainpage.shuttlereservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Cancelling extends AppCompatActivity
{
    FirebaseFirestore db;
    public String reason;

    public void Cancellation(View view)
    {
        FirebaseUser use = FirebaseAuth.getInstance().getCurrentUser();
        assert use != null;
        String email = use.getEmail();

        EditText edR = findViewById(R.id.editText6);
        reason = edR.getText().toString();

        Map<String, Object> user = new HashMap<>();
        user.put("Email",email);
        user.put("Reason",reason);
        user.put("TimeStamp",FieldValue.serverTimestamp());
        /*
        db.collection("Bookings").whereEqualTo("Email",email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if(task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot doc:task.getResult())
                            {
                                doc.getId();
                                doc.get(FieldPath.documentId());
                            }
                        }
                    }
                });

         */

        db.collection("Cancellation").add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                {
                    @Override
                    public void onSuccess(DocumentReference doc)
                    {
                        Toast.makeText(Cancelling.this, "Cancellation Successful "
                                + doc.getId(), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Cancelling.this, Home.class);
                        startActivity(i);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Cancelling.this, "Cancellation UnSuccessful," +
                                        " Please Try Again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelling);
        db = FirebaseFirestore.getInstance();
    }
}
