package com.mainpage.shuttlereservation.domains.managers;

import android.hardware.Camera;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mainpage.shuttlereservation.network.APIConstants;
import com.mainpage.shuttlereservation.ShuttleResApplication;
import com.mainpage.shuttlereservation.network.MySingleton;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;

import org.json.JSONObject;

public class UserManager
{
    public UserManager(){};

    public void signIn(String email, String pw, VolleyResponseListener responseListener){

        String reqURL = APIConstants.HOST + APIConstants.SIGN_IN + email +
                "&password=" + pw;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, reqURL, null,
                response -> {
                    responseListener.onSuccess("LogIn Successful");
                }, error -> {
            NetworkResponse networkResponse = error.networkResponse;
            try{
                if(networkResponse!=null && networkResponse.data!=null){
                    String jsonError = new String(networkResponse.data);
                    JSONObject object = new JSONObject(jsonError);
                    String er = object.getJSONObject("data").getString("message");
                    responseListener.onError(er);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        MySingleton.getInstance(ShuttleResApplication.getCtx()).addToRequestQueue(jsonObjectRequest);
    }

    public void signOut(String email, VolleyResponseListener responseListener){

        String reqURL = APIConstants.HOST + APIConstants.SIGN_OUT + email;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, reqURL, null,
                response -> {
                    responseListener.onSuccess("LogIn Successful");
                }, error -> {
            NetworkResponse networkResponse = error.networkResponse;
            try{
                if(networkResponse!=null && networkResponse.data!=null){
                    String jsonError = new String(networkResponse.data);
                    JSONObject object = new JSONObject(jsonError);
                    String er = object.getJSONObject("data").getString("message");
                    responseListener.onError(er);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        MySingleton.getInstance(ShuttleResApplication.getCtx()).addToRequestQueue(jsonObjectRequest);
    }

    //TODO: SignUp Implementation

    public void verifyOTP(String otp, VolleyResponseListener responseListener){
        String reqURL = APIConstants.HOST + APIConstants.EMAIL_VERIFICATION + otp;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, reqURL, null,
                response -> {
                    responseListener.onSuccess("Email Confirmed");
                }, error -> {
                    NetworkResponse networkResponse = error.networkResponse;
                    try{
                        if(networkResponse!=null && networkResponse.data!=null){
                            String jsonError = new String(networkResponse.data);
                            JSONObject object = new JSONObject(jsonError);
                            String er = object.getJSONObject("data").getString("message");

                            responseListener.onError(er);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        MySingleton.getInstance(ShuttleResApplication.getCtx()).addToRequestQueue(jsonObjectRequest);
    }
}
