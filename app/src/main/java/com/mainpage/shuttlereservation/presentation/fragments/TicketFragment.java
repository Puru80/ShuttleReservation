package com.mainpage.shuttlereservation.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;
import com.mainpage.shuttlereservation.network.GetTicketCallback;
import com.mainpage.shuttlereservation.presentation.TicketAdapter;

import java.util.ArrayList;
import java.util.List;

public class TicketFragment extends Fragment {
    private final List<Integer> ticketResponses = new ArrayList<>();
    String email = ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUser().getUserEmail();
    RecyclerView ticketList ;
    TicketAdapter tAdapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_ticket, container, false);

        /*ShuttleResApplication.getInstance().getAppBeanFactory().getTicketManager().getTickets(email, new GetTicketCallback() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onSuccess(List<TicketResponse> list) {
                ticketResponses = list;
            }
        });*/

        for(int i=0;i<10;i++){
            ticketResponses.add(i);
        }

        setupUI(view);

        return view;
    }

    public void setupUI(View view){
        ticketList = view.findViewById(R.id.ticketList);
        tAdapter = new TicketAdapter(getActivity(), ticketResponses);
        ticketList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ticketList.setAdapter(tAdapter);
    }

    public void listener(){

    }

    /*@Override
    public void onStart() {
        super.onStart();

        setupUI(view);
    }*/
}