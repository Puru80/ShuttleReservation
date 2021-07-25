package com.mainpage.shuttlereservation.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.presentation.TicketAdapter;

public class TicketFragment extends Fragment {
    RecyclerView ticketList ;
    TicketAdapter tAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ticket, container, false);

        setupUI(view);

        return view;
    }

    public void setupUI(View view){
        ticketList = view.findViewById(R.id.ticketList);
        TextView noTickets = view.findViewById(R.id.noTickets);

        if(ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().ticketResponses.isEmpty()) {
            noTickets.setVisibility(View.VISIBLE);
            String noTicket = "No Tickets to Show";
            noTickets.setText(noTicket);
        }

        tAdapter = new TicketAdapter(getActivity(), ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().ticketResponses);
        ticketList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ticketList.setAdapter(tAdapter);
    }
}