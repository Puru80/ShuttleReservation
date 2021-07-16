package com.mainpage.shuttlereservation.domains.managers;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.network.APIConstants;
import com.mainpage.shuttlereservation.domains.models.response.User;
import com.mainpage.shuttlereservation.network.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataManager
{
    private static final DataManager instance = new DataManager();
    private static final User user = new User();
    private static final List<String> destinations = new ArrayList<>();
    private static final List<String> timings = new ArrayList<>();
    private static final List<String> seats = new ArrayList<>();

    public DataManager() {}

    public DataManager getInstance(){
        return instance;
    }

    public void getUserDetails(String email){
        String url = APIConstants.HOST + APIConstants.GET_USER_DETAILS + email;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject object = response.getJSONObject("data");
                user.setFirstName(object.getString("firstName"));
                user.setLastName(object.getString("lastName"));
                user.setUserEmail(object.getString("email"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });

        MySingleton.getInstance(ShuttleResApplication.getCtx()).addToRequestQueue(request);
        
    }

    //TODO: Implement 'Keep me signedIn Logic'

    public List<String> getDestinations(){
        destinations.add("Select Destination");
        destinations.add("Pari Chowk");
        destinations.add("Noida City Center");
        destinations.add("Botanical Garden");

        return destinations;
    }

    public List<String> getTimings() {
        timings.add("Select Time");
        timings.add("Friday Out");
        timings.add("Saturday In");
        timings.add("Saturday Out");
        timings.add("Sunday In");
        return timings;
    }

    public List<String> getSeats() {
        seats.add("Select Seat");
        seats.add("1");
        seats.add("2");
        return seats;
    }

    public User getUser() {
        return user;
    }
}
