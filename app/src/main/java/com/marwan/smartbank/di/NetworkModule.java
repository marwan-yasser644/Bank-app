package com.marwan.smartbank.di;

import com.marwan.smartbank.data.remote.api.AuthApiService;
import com.marwan.smartbank.data.remote.api.PaymentApiService;
import com.marwan.smartbank.data.remote.interceptors.AuthInterceptor;
import com.marwan.smartbank.data.local.preferences.PreferencesManager;
import com.marwan.smartbank.utils.constants.ApiConstants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(
            AuthInterceptor authInterceptor) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public AuthApiService provideAuthApiService(Retrofit retrofit) {
        return retrofit.create(AuthApiService.class);
    }

    @Singleton
    @Provides
    public PaymentApiService providePaymentApiService(Retrofit retrofit) {
        return retrofit.create(PaymentApiService.class);
    }

    @Singleton
    @Provides
    public AuthInterceptor provideAuthInterceptor(PreferencesManager preferencesManager) {
        return new AuthInterceptor(preferencesManager);
    }
}
