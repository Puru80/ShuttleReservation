package com.mainpage.shuttlereservation.domains.managers;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.domains.models.request.Ticket;
import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;
import com.mainpage.shuttlereservation.network.APIConstants;
import com.mainpage.shuttlereservation.network.GetTicketCallback;
import com.mainpage.shuttlereservation.network.MySingleton;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TicketManager {
    public TicketManager(){}

    public void bookTicket(Ticket ticket, VolleyResponseListener volleyResponseListener){
        String reqUrl = APIConstants.HOST + APIConstants.BOOK_TICKET;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("origin", ticket.getOrigin());
            jsonBody.put("destination", ticket.getDestination());
            jsonBody.put("timing", ticket.getTiming());
            jsonBody.put("seats", ticket.getSeats());
            jsonBody.put("email", ticket.getEmail());

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, reqUrl, jsonBody,
                    response -> volleyResponseListener.onSuccess("Ticket Booked Successfully"),
                    error -> {
                        NetworkResponse networkResponse = error.networkResponse;
                        try{
                            if(networkResponse!=null && networkResponse.data!=null){
                                String jsonError = new String(networkResponse.data);
                                JSONObject object = new JSONObject(jsonError);
                                String er = object.getJSONObject("data").getString("message");
                                volleyResponseListener.onError(er);
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

            MySingleton.getInstance(ShuttleResApplication.getCtx()).addToRequestQueue(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getTickets(String email, GetTicketCallback getTicketCallback){
        String url = APIConstants.HOST + APIConstants.GET_TICKETS + email;
        List<TicketResponse> ticketList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray arr = response.getJSONArray("data");

                        for(int i=0;i<arr.length();i++){
                            TicketResponse ticket = new TicketResponse();
                            JSONObject obj = arr.getJSONObject(i);

                            ticket.setId(obj.getLong("id"));
                            ticket.setOrigin(obj.getString("origin"));
                            ticket.setDestination(obj.getString("destination"));
                            ticket.setSeats(obj.getLong("seats"));
                            ticket.setTiming(obj.getString("timing"));
                            ticket.setTimeOfBooking(obj.getString("timeOfBooking"));
                            ticket.setPaymentStatus((obj.getBoolean("paymentStatus"))?"Done":"Pending");

                            ticketList.add(ticket);
                        }

                        getTicketCallback.onSuccess(ticketList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    getTicketCallback.onError("Could Not Fetch Tickets");
        });

        MySingleton.getInstance(ShuttleResApplication.getCtx()).addToRequestQueue(jsonObjectRequest);
    }


}
