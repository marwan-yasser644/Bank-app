package com.marwan.smartbank.domain.repositories;

import com.marwan.smartbank.data.remote.responses.AuthTokenResponse;
import com.marwan.smartbank.data.remote.responses.OtpResponse;
import com.marwan.smartbank.domain.entities.User;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface AuthRepository {
    Single<OtpResponse> requestOtp(String phoneNumber);
    Single<AuthTokenResponse> verifyOtp(String phoneNumber, String otp);
    Single<AuthTokenResponse> refreshToken(String refreshToken);
    Single<User> getCurrentUser();
    Completable logout();
}
