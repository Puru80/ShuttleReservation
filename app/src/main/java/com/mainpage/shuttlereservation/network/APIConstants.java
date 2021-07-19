package com.mainpage.shuttlereservation.network;

public class APIConstants {
    public static final String HOST = "https://shuttleres-0.herokuapp.com/api/v1/";
    public static final String SIGN_IN = "registration/login?email=";
    public static final String SIGN_UP = "registration";
    public static final String SIGN_OUT = "registration/logout?email=";
    public static final String EMAIL_VERIFICATION = "registration/confirm?token=";
    public static final String GET_USER_DETAILS = "registration/userdetails?email=";
    public static final String BOOK_TICKET = "ticket";
    public static final String GET_TICKETS = "ticket/mytickets?email";
}
