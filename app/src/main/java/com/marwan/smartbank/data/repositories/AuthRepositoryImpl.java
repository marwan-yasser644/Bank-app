package com.marwan.smartbank.data.repositories;

import com.marwan.smartbank.data.local.dao.UserDao;
import com.marwan.smartbank.data.remote.api.AuthApiService;
import com.marwan.smartbank.data.remote.responses.AuthTokenResponse;
import com.marwan.smartbank.data.remote.responses.OtpResponse;
import com.marwan.smartbank.domain.entities.User;
import com.marwan.smartbank.domain.repositories.AuthRepository;
import com.marwan.smartbank.security.tokenmanager.TokenManager;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class AuthRepositoryImpl implements AuthRepository {
    private final AuthApiService apiService;
    private final UserDao userDao;
    private final TokenManager tokenManager;

    public AuthRepositoryImpl(AuthApiService apiService, UserDao userDao, TokenManager tokenManager) {
        this.apiService = apiService;
        this.userDao = userDao;
        this.tokenManager = tokenManager;
    }

    @Override
    public Single<OtpResponse> requestOtp(String phoneNumber) {
        return apiService.requestOtp(new AuthApiService.OtpRequest(phoneNumber))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<AuthTokenResponse> verifyOtp(String phoneNumber, String otp) {
        return apiService.verifyOtp(new AuthApiService.VerifyOtpRequest(phoneNumber, otp))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(response -> {
                    tokenManager.saveTokens(response.getAccessToken(), response.getRefreshToken());
                    if (response.getUser() != null) {
                        userDao.insertUser(response.getUser());
                    }
                });
    }

    @Override
    public Single<AuthTokenResponse> refreshToken(String refreshToken) {
        return apiService.refreshToken(new AuthApiService.RefreshTokenRequest(refreshToken))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(response -> {
                    tokenManager.saveTokens(response.getAccessToken(), response.getRefreshToken());
                    if (response.getUser() != null) {
                        userDao.insertUser(response.getUser());
                    }
                });
    }

    @Override
    public Single<User> getCurrentUser() {
        return Single.fromCallable(() -> {
            User user = userDao.getUserById(""); // Fetch from DB
            if (user == null) {
                throw new Exception("User not found");
            }
            return user;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable logout() {
        return Completable.fromAction(() -> {
            tokenManager.clearTokens();
            userDao.clearAll();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
