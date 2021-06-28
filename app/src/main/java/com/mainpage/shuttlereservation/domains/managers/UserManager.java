package com.mainpage.shuttlereservation.domains.managers;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
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

    public void signUp(JSONObject jsonBody, VolleyResponseListener responseListener) {
        String url = APIConstants.HOST + APIConstants.SIGN_UP ;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                response -> responseListener.onSuccess("Email Registered, Please verify your email"),
                error -> {
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

        MySingleton.getInstance(ShuttleResApplication.getCtx()).addToRequestQueue(request);
    }

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
