package com.marwan.smartbank.di;

import com.marwan.smartbank.data.local.dao.UserDao;
import com.marwan.smartbank.data.local.dao.TransactionDao;
import com.marwan.smartbank.data.local.dao.AccountDao;
import com.marwan.smartbank.data.remote.api.AuthApiService;
import com.marwan.smartbank.data.remote.api.PaymentApiService;
import com.marwan.smartbank.data.repositories.AuthRepositoryImpl;
import com.marwan.smartbank.data.repositories.PaymentRepositoryImpl;
import com.marwan.smartbank.domain.repositories.AuthRepository;
import com.marwan.smartbank.domain.repositories.PaymentRepository;
import com.marwan.smartbank.security.tokenmanager.TokenManager;
import com.marwan.smartbank.data.local.preferences.PreferencesManager;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Singleton
    @Provides
    public AuthRepository provideAuthRepository(
            AuthApiService authApiService,
            UserDao userDao,
            TokenManager tokenManager) {
        return new AuthRepositoryImpl(authApiService, userDao, tokenManager);
    }

    @Singleton
    @Provides
    public PaymentRepository providePaymentRepository(
            PaymentApiService paymentApiService,
            TransactionDao transactionDao,
            AccountDao accountDao) {
        return new PaymentRepositoryImpl(paymentApiService, transactionDao, accountDao);
    }

    @Singleton
    @Provides
    public TokenManager provideTokenManager(PreferencesManager preferencesManager) {
        return new TokenManager(preferencesManager);
    }
}
