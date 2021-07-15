package com.mainpage.shuttlereservation.domains.managers;

import com.mainpage.shuttlereservation.network.APIConstants;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

import org.json.JSONObject;

public class TicketManager {
    public TicketManager(){};

    public void bookTicket(JSONObject object, VolleyResponseListener volleyResponseListener){
        String reqUrl = APIConstants.HOST + APIConstants.BOOK_TICKET;


    }
}
