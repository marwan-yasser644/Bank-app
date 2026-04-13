package com.marwan.smartbank.data.remote.responses;

import com.google.gson.annotations.SerializedName;

public class TransferResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("transaction_id")
    private String transactionId;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public TransferResponse() {
    }

    public TransferResponse(boolean success, String transactionId, String message, String status) {
        this.success = success;
        this.transactionId = transactionId;
        this.message = message;
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
