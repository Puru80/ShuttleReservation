package com.mainpage.shuttlereservation.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mainpage.shuttlereservation.R;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.Holder> {
    private final List<Integer> tickets;
    private final Context ctx;

    public TicketAdapter(Context ctx, List<Integer> list){
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
//        holder.origin.setText("Puru Agarwal");
        holder.origin.setText(tickets.get(position));
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private final TextView origin;

        public Holder(@NonNull View itemView) {
            super(itemView);

            origin = itemView.findViewById(R.id.ticket_origin);
        }
    }
}