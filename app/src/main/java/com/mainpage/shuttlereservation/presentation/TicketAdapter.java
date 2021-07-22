package com.mainpage.shuttlereservation.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.Holder> {
    private final List<TicketResponse> tickets;
    private final Context ctx;

    public TicketAdapter(Context ctx, List<TicketResponse> list){
        this.tickets = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(ctx).inflate(R.layout.ticket_item,
                parent, false);



        return new Holder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        TicketResponse response = tickets.get(position);

        holder.id.setText(String.valueOf(response.getId()));
        holder.origin.setText("From :" + response.getOrigin());
        holder.destination.setText("To      :" + response.getDestination());
        holder.time.setText("Time :" + response.getTiming());
        holder.tob.setText("TOB  :" + response.getTimeOfBooking());

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private final TextView origin;
        private final TextView destination;
        private final TextView tob;
        private final TextView time;
        private final TextView id;

        public Holder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.ticket_id);
            origin = itemView.findViewById(R.id.ticket_origin);
            destination = itemView.findViewById(R.id.ticket_destination);
            tob = itemView.findViewById(R.id.ticket_tob);
            time = itemView.findViewById(R.id.ticket_time);
        }
    }
}