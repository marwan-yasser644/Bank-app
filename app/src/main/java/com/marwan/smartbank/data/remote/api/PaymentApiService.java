package com.marwan.smartbank.data.remote.api;

import com.marwan.smartbank.data.remote.responses.TransferResponse;
import com.marwan.smartbank.domain.entities.Account;
import com.marwan.smartbank.domain.entities.Transaction;

import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.Path;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface PaymentApiService {

    @POST("/payments/send-money")
    Single<TransferResponse> sendMoney(@Body SendMoneyRequest request);

    @POST("/payments/bill-payment")
    Single<TransferResponse> payBill(@Body BillPaymentRequest request);

    @GET("/accounts/{userId}")
    Single<List<Account>> getAccounts(@Path("userId") String userId);

    @GET("/transactions/{userId}")
    Single<List<Transaction>> getTransactions(@Path("userId") String userId);

    class SendMoneyRequest {
        public String recipientPhone;
        public double amount;
        public String description;

        public SendMoneyRequest(String recipientPhone, double amount, String description) {
            this.recipientPhone = recipientPhone;
            this.amount = amount;
            this.description = description;
        }
    }

    class BillPaymentRequest {
        public String billerId;
        public double amount;
        public String referenceNumber;

        public BillPaymentRequest(String billerId, double amount, String referenceNumber) {
            this.billerId = billerId;
            this.amount = amount;
            this.referenceNumber = referenceNumber;
        }
    }
}
