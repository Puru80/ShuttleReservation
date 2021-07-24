package com.mainpage.shuttlereservation.presentation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

public class PopUpClass {
    private final TicketResponse response;
    private TextView id, origin, destination, time, tob, seats, paymentStatusTV;
    private AppCompatButton btnOk, btnCancel;
    private PopupWindow popupWindow;
    private View popupView;

    public PopUpClass(TicketResponse response){
        this.response = response;

    }

    //Method to display popup
    public void showPopUpWindow(final View view){
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

        setUpUI();
    }

    public void setUpUI(){
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

        //TODO: Implement delete functionality
        btnCancel.setOnClickListener(view ->{
            ShuttleResApplication.getInstance().getAppBeanFactory().getTicketManager().cancelTicket(response.getId(),
                    new VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(popupView.getContext(), message, Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }

                @Override
                public void onSuccess(String message) {
                    Toast.makeText(popupView.getContext(), message, Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }
            });
        });
    }
}
