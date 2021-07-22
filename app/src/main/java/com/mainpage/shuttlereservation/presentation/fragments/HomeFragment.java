package com.mainpage.shuttlereservation.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.presentation.Booking;

public class HomeFragment extends Fragment
{
    private AppCompatButton btnBook;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setupUI(view);
        listeners();

        return view;
    }

    public void setupUI(View view){
        btnBook = view.findViewById(R.id.buttonBook);
    }

    public void listeners(){
        btnBook.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), Booking.class);
            startActivity(i);
            onPause();
        });
    }
}