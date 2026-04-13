package com.marwan.smartbank.data.repositories;

import com.marwan.smartbank.data.local.dao.TransactionDao;
import com.marwan.smartbank.data.local.dao.AccountDao;
import com.marwan.smartbank.data.remote.api.PaymentApiService;
import com.marwan.smartbank.data.remote.responses.TransferResponse;
import com.marwan.smartbank.domain.entities.Account;
import com.marwan.smartbank.domain.entities.Transaction;
import com.marwan.smartbank.domain.repositories.PaymentRepository;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentApiService apiService;
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    public PaymentRepositoryImpl(PaymentApiService apiService, TransactionDao transactionDao,
                               AccountDao accountDao) {
        this.apiService = apiService;
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }

    @Override
    public Single<TransferResponse> sendMoney(String recipientPhone, double amount, String description) {
        return apiService.sendMoney(
                new PaymentApiService.SendMoneyRequest(recipientPhone, amount, description))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(response -> {
                    if (response.isSuccess()) {
                        // Save to local database
                    }
                });
    }

    @Override
    public Single<TransferResponse> payBill(String billerId, double amount, String referenceNumber) {
        return apiService.payBill(
                new PaymentApiService.BillPaymentRequest(billerId, amount, referenceNumber))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Account>> getAccounts(String userId) {
        return apiService.getAccounts(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(accounts -> {
                    for (Account account : accounts) {
                        accountDao.insertAccount(account);
                    }
                });
    }

    @Override
    public Single<List<Transaction>> getTransactions(String userId) {
        return apiService.getTransactions(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(transactions -> {
                    for (Transaction transaction : transactions) {
                        transactionDao.insertTransaction(transaction);
                    }
                });
    }

    @Override
    public Single<Transaction> getTransactionDetails(String transactionId) {
        return Single.fromCallable(() -> transactionDao.getTransactionById(transactionId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
