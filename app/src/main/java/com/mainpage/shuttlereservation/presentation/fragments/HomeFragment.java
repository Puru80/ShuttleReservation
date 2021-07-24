package com.mainpage.shuttlereservation.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.mainpage.shuttlereservation.Home;
import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;
import com.mainpage.shuttlereservation.network.GetTicketCallback;
import com.mainpage.shuttlereservation.presentation.Booking;

import java.util.List;

public class HomeFragment extends Fragment {
    private AppCompatButton btnBook;
    private ProgressBar progressBar;

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
        progressBar = view.findViewById(R.id.progress_circular);
    }

    public void listeners(){
        btnBook.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), Booking.class);
            startActivity(i);
            onPause();
        });


        progressBar.setVisibility(View.VISIBLE);

        ShuttleResApplication.getInstance().getAppBeanFactory().getTicketManager().getTickets(ShuttleResApplication.getInstance().
                getAppBeanFactory().getDataManager().getUser().getUserEmail(), new GetTicketCallback() {
            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), "Error in fetching data", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(List<TicketResponse> list) {
                ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().setTicketResponse(list);
                Toast.makeText(getActivity(), "Data Fetched", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}