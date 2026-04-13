package com.marwan.smartbank.data.remote.api;

import com.marwan.smartbank.data.remote.responses.OtpResponse;
import com.marwan.smartbank.data.remote.responses.AuthTokenResponse;

import retrofit2.http.POST;
import retrofit2.http.Body;
import io.reactivex.rxjava3.core.Single;

public interface AuthApiService {

    @POST("/auth/request-otp")
    Single<OtpResponse> requestOtp(@Body OtpRequest request);

    @POST("/auth/verify-otp")
    Single<AuthTokenResponse> verifyOtp(@Body VerifyOtpRequest request);

    @POST("/auth/refresh-token")
    Single<AuthTokenResponse> refreshToken(@Body RefreshTokenRequest request);

    class OtpRequest {
        public String phoneNumber;

        public OtpRequest(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    class VerifyOtpRequest {
        public String phoneNumber;
        public String otp;

        public VerifyOtpRequest(String phoneNumber, String otp) {
            this.phoneNumber = phoneNumber;
            this.otp = otp;
        }
    }

    class RefreshTokenRequest {
        public String refreshToken;

        public RefreshTokenRequest(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}
