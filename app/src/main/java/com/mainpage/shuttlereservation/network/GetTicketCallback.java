package com.mainpage.shuttlereservation.network;

import com.mainpage.shuttlereservation.domains.models.response.TicketResponse;

import java.util.List;

public interface GetTicketCallback {
    void onError(String message);

    void onSuccess(List<TicketResponse> list);
}
