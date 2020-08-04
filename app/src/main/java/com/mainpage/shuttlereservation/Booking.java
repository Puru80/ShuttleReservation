package com.mainpage.shuttlereservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Booking extends AppCompatActivity
{
    FirebaseFirestore db;
    public String dest,time;
    public int seats;
    public String id;

    public void Ticketing(View view)
    {

        FirebaseUser use = FirebaseAuth.getInstance().getCurrentUser();
        assert use != null;
        String email = use.getEmail();

        EditText ed3 = findViewById(R.id.editText4);
        dest = ed3.getText().toString();
        EditText ed2 = findViewById(R.id.editText3);
        time = ed2.getText().toString();
        EditText ed5 = findViewById(R.id.editText5);
        seats = Integer.parseInt(ed5.getText().toString());

        final Map<String, Object> user = new HashMap<>();
        user.put("Email",email);
        user.put("Destination",dest);
        user.put("Time",time);
        user.put("Number",seats);
        user.put("Stamp",FieldValue.serverTimestamp());

        if(seats>2 || seats<=0)
        {
            ed5.setText("");
            Toast.makeText(this, "Maximum 2 Seats", Toast.LENGTH_SHORT).show();
        }
        else
        {
            db.collection("Bookings").whereEqualTo("Email",email)
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
            {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task)
                        {
                            if(task.isSuccessful())
                            {
                                for(QueryDocumentSnapshot doc:task.getResult())
                                {
                                    Toast.makeText(Booking.this, "You have already booked tickets"
                                            + "with Id " + doc.getId(),
                                            Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Booking.this,Home.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                            else
                            {
                                db.collection("Bookings").add(user)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference doc) {
                                                Toast.makeText(Booking.this, "Booking Successful"
                                                        + " Ticket Id: "
                                                        + doc.getId(), Toast.LENGTH_LONG).show();
                                                id = doc.getId();
                                                //id = doc.getPath();
                                                Intent i = new Intent(Booking.this, Home.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener()
                                        {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Booking.this, "Booking UnSuccessful, Please Try Again"
                                                        , Toast.LENGTH_SHORT).show();

                                            }
                                        });
                            }
                        }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        db = FirebaseFirestore.getInstance();
    }
}
