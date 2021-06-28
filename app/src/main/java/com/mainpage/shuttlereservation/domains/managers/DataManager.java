package com.mainpage.shuttlereservation.domains.managers;

import com.mainpage.shuttlereservation.network.APIConstants;
import com.mainpage.shuttlereservation.domains.models.User;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

public class DataManager
{
    private static final DataManager instance = new DataManager();
    private static final User user = new User();

    public DataManager() {
    }

    public DataManager getInstance(){
        return instance;
    }

    //TODO: To be implemented
    public void getUserDetails(String email, VolleyResponseListener responseListener){
        String url = APIConstants.HOST + APIConstants.GET_USER_DETAILS + email;
        
    }

    //TODO: Implement 'Keem me signedIn Logic'
}
