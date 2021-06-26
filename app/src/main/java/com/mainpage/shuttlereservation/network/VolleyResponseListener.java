package com.mainpage.shuttlereservation.network;

public interface VolleyResponseListener {
    void onError(String message);

    void onSuccess(String message);
}
