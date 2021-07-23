package com.mainpage.shuttlereservation.presentation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mainpage.shuttlereservation.R;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;

public class PopUpClass {
    private TicketResponse response;

    public PopUpClass(){}

    public PopUpClass(TicketResponse response){
        this.response = response;
    }

    //Method to display popup
    public void showPopUpWindow(final View view){
        //Inflating the layout
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        View popupView = inflater.inflate(R.layout.activity_pup_up_class, null);

        //Specify the length and width through constants
        int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        int height = ConstraintLayout.LayoutParams.MATCH_PARENT;

        //Create a window with our parameters
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView textView = popupView.findViewById(R.id.ticket_id);
        textView.setText("Hi " + ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUser().getFirstName());

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
