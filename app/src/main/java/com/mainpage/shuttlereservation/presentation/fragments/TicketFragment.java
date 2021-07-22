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
import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;
import com.mainpage.shuttlereservation.network.GetTicketCallback;
import com.mainpage.shuttlereservation.presentation.TicketAdapter;

import java.util.ArrayList;
import java.util.List;

public class TicketFragment extends Fragment {
    private List<TicketResponse> ticketResponses = new ArrayList<>();
    String email = ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUser().getUserEmail();
    RecyclerView ticketList ;
    TicketAdapter tAdapter;
    private TextView noTickets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ticket, container, false);

        setupUI(view);

        return view;
    }

    //TODO: Resolve this
    public void setupUI(View view){
        ticketList = view.findViewById(R.id.ticketList);
        noTickets = view.findViewById(R.id.noTickets);

        ShuttleResApplication.getInstance().getAppBeanFactory().getTicketManager().
                getTickets(email, new GetTicketCallback() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onSuccess(List<TicketResponse> list) {
                if(list.isEmpty()) {
                    noTickets.setVisibility(View.VISIBLE);
                    noTickets.setText(R.string.EmptyTicket);
                }
                else {
                    for(int i=list.size()-1;i>=0;i--)
                        ticketResponses.add(list.get(i));
                    tAdapter = new TicketAdapter(getActivity(), ticketResponses);
                    ticketList.setLayoutManager(new LinearLayoutManager(getActivity()));
                    ticketList.setAdapter(tAdapter);
                }
            }
        });
    }
}