package com.mainpage.shuttlereservation.presentation;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.Holder> {
    private final List<TicketResponse> tickets;
    private final Context ctx;

    //PopUpWindow
//    private final TicketResponse response;
    private TextView id, origin, destination, time, tob, seats, paymentStatusTV;
    private AppCompatButton btnOk, btnCancel;
    private PopupWindow popupWindow;
    private View popupView;

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

        String id = "Id       : " + response.getId();
        String origin = "From : " + response.getOrigin();
        String destination = "To      : " + response.getDestination();
        String time = "Time : " + response.getTiming();
        String tob = "TOB  : " + response.getTimeOfBooking();

        holder.id.setText(id);
        holder.origin.setText(origin);
        holder.destination.setText(destination);
        holder.time.setText(time);
        holder.tob.setText(tob);

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
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

            itemView.setOnClickListener(v -> {
                showPopUpWindow(v, tickets.get(getAdapterPosition()));
//                notifyDataSetChanged();
            });
        }
    }

    //PopupWindow
    public void showPopUpWindow(final View view, TicketResponse response){
        //Inflating the layout
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        popupView = inflater.inflate(R.layout.activity_pop_up_class, null);

        //Specify the length and width through constants
        int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        int height = ConstraintLayout.LayoutParams.MATCH_PARENT;

        //Create a window with our parameters
        popupWindow = new PopupWindow(popupView, width, height, true);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //Setting the layout
        id = popupView.findViewById(R.id.ticket_id);
        origin = popupView.findViewById(R.id.ticket_origin);
        destination = popupView.findViewById(R.id.ticket_destination);
        time = popupView.findViewById(R.id.ticket_time);
        tob = popupView.findViewById(R.id.ticket_tob);
        seats = popupView.findViewById(R.id.ticket_seats);
        paymentStatusTV = popupView.findViewById(R.id.ticket_ps);
        time = popupView.findViewById(R.id.ticket_time);

        btnOk = popupView.findViewById(R.id.btn_OK);
        btnCancel = popupView.findViewById(R.id.btn_Cancel);

        setUpUI(response);
    }

    //Setting up data in Popup Window
    public void setUpUI(TicketResponse response){
        String idStr = "Id       : " + response.getId();
        String originStr = "From : " + response.getOrigin();
        String destinationStr = "To      : " + response.getDestination();
        String timeStr = "Time : " + response.getTiming();
        String tobStr = "TOB  : " + response.getTimeOfBooking();
        String seatsStr = "Seats: " + response.getSeats();
        String paymentStatus = "Payment Status: " + response.getPaymentStatus();

        id.setText(idStr);
        origin.setText(originStr);
        destination.setText(destinationStr);
        time.setText(timeStr);
        tob.setText(tobStr);
        seats.setText(seatsStr);
        paymentStatusTV.setText(paymentStatus);

        btnOk.setOnClickListener(v -> popupWindow.dismiss());

        btnCancel.setOnClickListener(view -> ShuttleResApplication.getInstance().getAppBeanFactory().getTicketManager().cancelTicket(response.getId(),
                new VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(popupView.getContext(), message, Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        popupWindow.dismiss();
                    }

                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(popupView.getContext(), message, Toast.LENGTH_SHORT).show();
                        ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().ticketResponses.remove(response);
                        notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                }));
    }
}