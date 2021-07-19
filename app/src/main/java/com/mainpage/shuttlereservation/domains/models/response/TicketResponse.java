package com.mainpage.shuttlereservation.domains.models.response;

public class TicketResponse {

    private Long id;
    private String origin;
    private String destination;
    private String timing;
    private String timeOfBooking;
    private String paymentStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getTimeOfBooking() {
        return timeOfBooking;
    }

    public void setTimeOfBooking(String timeOfBooking) {
        this.timeOfBooking = timeOfBooking;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
