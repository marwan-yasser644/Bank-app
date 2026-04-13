package com.marwan.smartbank.presentation.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marwan.smartbank.data.remote.responses.AuthTokenResponse;
import com.marwan.smartbank.data.remote.responses.OtpResponse;
import com.marwan.smartbank.domain.repositories.AuthRepository;
import com.marwan.smartbank.utils.validators.InputValidator;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import timber.log.Timber;

import javax.inject.Inject;

@HiltViewModel
public class AuthViewModel extends ViewModel {

    private final AuthRepository authRepository;

    private final MutableLiveData<AuthState> _authState = 
            new MutableLiveData<>(AuthState.INITIAL);
    public LiveData<AuthState> authState = _authState;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    private final MutableLiveData<String> _successMessage = new MutableLiveData<>();
    public LiveData<String> successMessage = _successMessage;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void requestOtp(String phoneNumber) {
        if (!InputValidator.isValidPhoneNumber(phoneNumber)) {
            _errorMessage.setValue("Invalid phone number");
            return;
        }

        _authState.setValue(AuthState.LOADING);

        disposables.add(
                authRepository.requestOtp(phoneNumber)
                        .subscribe(
                                response -> {
                                    if (response.isSuccess()) {
                                        _authState.setValue(AuthState.OTP_SENT);
                                        _successMessage.setValue(response.getMessage());
                                    } else {
                                        _authState.setValue(AuthState.ERROR);
                                        _errorMessage.setValue(response.getMessage());
                                    }
                                },
                                error -> {
                                    _authState.setValue(AuthState.ERROR);
                                    _errorMessage.setValue(error.getMessage());
                                    Timber.e(error, "Failed to request OTP");
                                }
                        )
        );
    }

    public void verifyOtp(String phoneNumber, String otp) {
        if (!InputValidator.isValidOtp(otp)) {
            _errorMessage.setValue("Invalid OTP format");
            return;
        }

        _authState.setValue(AuthState.LOADING);

        disposables.add(
                authRepository.verifyOtp(phoneNumber, otp)
                        .subscribe(
                                response -> {
                                    _authState.setValue(AuthState.AUTHENTICATED);
                                    _successMessage.setValue("Login successful");
                                },
                                error -> {
                                    _authState.setValue(AuthState.ERROR);
                                    _errorMessage.setValue(error.getMessage());
                                    Timber.e(error, "Failed to verify OTP");
                                }
                        )
        );
    }

    public void logout() {
        disposables.add(
                authRepository.logout()
                        .subscribe(
                                () -> {
                                    _authState.setValue(AuthState.LOGOUT);
                                    _successMessage.setValue("Logged out successfully");
                                },
                                error -> {
                                    _errorMessage.setValue(error.getMessage());
                                    Timber.e(error, "Failed to logout");
                                }
                        )
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

    public enum AuthState {
        INITIAL,
        OTP_SENT,
        LOADING,
        AUTHENTICATED,
        ERROR,
        LOGOUT
    }
}
