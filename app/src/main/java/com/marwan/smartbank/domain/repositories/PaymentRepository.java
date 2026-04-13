package com.marwan.smartbank.domain.repositories;

import com.marwan.smartbank.data.remote.responses.TransferResponse;
import com.marwan.smartbank.domain.entities.Account;
import com.marwan.smartbank.domain.entities.Transaction;

import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface PaymentRepository {
    Single<TransferResponse> sendMoney(String recipientPhone, double amount, String description);
    Single<TransferResponse> payBill(String billerId, double amount, String referenceNumber);
    Single<List<Account>> getAccounts(String userId);
    Single<List<Transaction>> getTransactions(String userId);
    Single<Transaction> getTransactionDetails(String transactionId);
}
