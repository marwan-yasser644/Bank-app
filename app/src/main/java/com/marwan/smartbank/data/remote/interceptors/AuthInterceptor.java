package com.marwan.smartbank.data.remote.interceptors;

import android.util.Log;

import com.marwan.smartbank.data.local.preferences.PreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final PreferencesManager preferencesManager;

    public AuthInterceptor(PreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = preferencesManager.getAccessToken();

        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder();

        if (!token.isEmpty()) {
            requestBuilder.header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json");
        }

        Request newRequest = requestBuilder
                .header("User-Agent", "SmartBank/1.0.0")
                .build();

        return chain.proceed(newRequest);
    }
}
