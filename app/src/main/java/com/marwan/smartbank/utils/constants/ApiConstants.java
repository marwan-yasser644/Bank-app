package com.marwan.smartbank.utils.constants;

public class ApiConstants {
    public static final String BASE_URL = "https://api.smartbank.example.com/";
    public static final int CONNECT_TIMEOUT = 30;
    public static final int READ_TIMEOUT = 30;
    public static final int WRITE_TIMEOUT = 30;

    // Endpoints
    public static final String REQUEST_OTP = "auth/request-otp";
    public static final String VERIFY_OTP = "auth/verify-otp";
    public static final String REFRESH_TOKEN = "auth/refresh-token";
    public static final String SEND_MONEY = "payments/send-money";
    public static final String PAY_BILL = "payments/bill-payment";
    public static final String GET_ACCOUNTS = "accounts/";
    public static final String GET_TRANSACTIONS = "transactions/";
}
