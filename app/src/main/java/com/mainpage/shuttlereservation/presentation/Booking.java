package com.mainpage.shuttlereservation.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.domains.models.response.Ticket;

import org.json.JSONObject;

import java.util.List;

public class Booking extends AppCompatActivity
{
    private AppCompatSpinner destinationSpinner, timingSpinner, seatSpinner;
    private AppCompatButton btnBook;
    private Ticket ticket;
    private List<String> destinations, timing, seats;
    private JSONObject ticketObject = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        destinations = ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getDestinations();
        timing = ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getTimings();
        seats = ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getSeats();

        setupUI();
        listeners();

    }

    void setupUI(){
        //Destination Selection
        destinationSpinner = findViewById(R.id.destSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                destinations);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(arrayAdapter);
        destinationSpinner.setPrompt("Select Destinations");
        destinationSpinner.setSelection(-1);

        //Time selection
        timingSpinner = findViewById(R.id.timeSpinner);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                timing);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timingSpinner.setAdapter(arrayAdapter);

        //Seat selection
        seatSpinner = findViewById(R.id.seatSpinner);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                seats);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatSpinner.setAdapter(arrayAdapter);

        ticket = new Ticket();

        btnBook = findViewById(R.id.bookTicket);
    }

    void listeners(){
        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ticket.setDestination(destinations.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        timingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ticket.setTiming(timing.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        seatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                    ticket.setSeats(Long.parseLong(seats.get(i)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBook.setOnClickListener(view -> {

        });
    }
}
