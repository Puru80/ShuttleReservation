package com.mainpage.shuttlereservation.domains.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;
import com.mainpage.shuttlereservation.network.APIConstants;
import com.mainpage.shuttlereservation.domains.models.response.User;
import com.mainpage.shuttlereservation.network.MySingleton;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataManager
{
    private static final Context ctx = ShuttleResApplication.getCtx();
    private static final DataManager instance = new DataManager();
    private static final User user = new User();
    private static List<TicketResponse> ticketResponses = new ArrayList<>();
    private static final List<String> origin = new ArrayList<>();
    private static final List<String> destinations = new ArrayList<>();
    private static final List<String> timings = new ArrayList<>();
    private static final List<String> seats = new ArrayList<>();
    SharedPreferences sh = ctx.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);

    public DataManager() {}

    public DataManager getInstance(){
        return instance;
    }

    public void getUserDetails(String email, VolleyResponseListener volleyResponseListener){
        String url = APIConstants.HOST + APIConstants.GET_USER_DETAILS + email;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject object = response.getJSONObject("data");
                user.setFirstName(object.getString("firstName"));
                user.setLastName(object.getString("lastName"));
                user.setUserEmail(object.getString("email"));

                volleyResponseListener.onSuccess("Success");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            volleyResponseListener.onError("Error Occurred");
        });

        MySingleton.getInstance(ShuttleResApplication.getCtx()).addToRequestQueue(request);
    }

    public void rememberMe(String email, String password){
        SharedPreferences.Editor editor = sh.edit();
        editor.putString("email", email);
        editor.putString("password", password);

        editor.apply();
    }

    public List<String> getDestinations(){
        if(destinations.isEmpty()){
            destinations.add("Select Destination");
            destinations.add("Bennett University");
            destinations.add("Pari Chowk");
            destinations.add("Noida City Center");
            destinations.add("Botanical Garden");
        }
        return destinations;
    }

    public List<String> getOrigin(){
        if(origin.isEmpty()){
            origin.add("Select Origin");
            origin.add("Bennett University");
            origin.add("Pari Chowk");
            origin.add("Noida City Centre");
            origin.add("Botanical Garden");
        }

        return origin;
    }

    public List<String> getTimings() {
        if(timings.isEmpty()){
            timings.add("Select Time");
            timings.add("Friday");
            timings.add("Saturday");
            timings.add("Sunday");
        }
        return timings;
    }

    public List<String> getSeats() {
        if (seats.isEmpty()){
            seats.add("Select Seat");
            seats.add("1");
            seats.add("2");
        }
        return seats;
    }

    public User getUser() {
        return user;
    }
}
