package com.marwan.smartbank.data.remote.responses;

import com.google.gson.annotations.SerializedName;

public class OtpResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("otp_id")
    private String otpId;

    @SerializedName("expires_in")
    private int expiresIn;

    public OtpResponse() {
    }

    public OtpResponse(boolean success, String message, String otpId, int expiresIn) {
        this.success = success;
        this.message = message;
        this.otpId = otpId;
        this.expiresIn = expiresIn;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtpId() {
        return otpId;
    }

    public void setOtpId(String otpId) {
        this.otpId = otpId;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
