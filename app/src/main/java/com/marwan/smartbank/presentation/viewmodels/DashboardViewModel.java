package com.marwan.smartbank.presentation.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marwan.smartbank.domain.entities.Account;
import com.marwan.smartbank.domain.entities.Transaction;
import com.marwan.smartbank.domain.repositories.PaymentRepository;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.List;

@HiltViewModel
public class DashboardViewModel extends ViewModel {

    private final PaymentRepository paymentRepository;

    private final MutableLiveData<List<Account>> _userAccounts = new MutableLiveData<>();
    public LiveData<List<Account>> userAccounts = _userAccounts;

    private final MutableLiveData<List<Transaction>> _recentTransactions = new MutableLiveData<>();
    public LiveData<List<Transaction>> recentTransactions = _recentTransactions;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
    public LiveData<Boolean> isLoading = _isLoading;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private String currentUserId = "";

    @Inject
    public DashboardViewModel(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void loadDashboardData(String userId) {
        this.currentUserId = userId;
        _isLoading.setValue(true);

        disposables.add(
                paymentRepository.getAccounts(userId)
                        .subscribe(
                                accounts -> {
                                    _userAccounts.setValue(accounts);
                                    loadTransactions(userId);
                                },
                                error -> {
                                    _isLoading.setValue(false);
                                    _errorMessage.setValue(error.getMessage());
                                    Timber.e(error, "Failed to load accounts");
                                }
                        )
        );
    }

    private void loadTransactions(String userId) {
        disposables.add(
                paymentRepository.getTransactions(userId)
                        .subscribe(
                                transactions -> {
                                    _recentTransactions.setValue(transactions);
                                    _isLoading.setValue(false);
                                },
                                error -> {
                                    _isLoading.setValue(false);
                                    _errorMessage.setValue(error.getMessage());
                                    Timber.e(error, "Failed to load transactions");
                                }
                        )
        );
    }

    public void refreshData() {
        if (!currentUserId.isEmpty()) {
            loadDashboardData(currentUserId);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
