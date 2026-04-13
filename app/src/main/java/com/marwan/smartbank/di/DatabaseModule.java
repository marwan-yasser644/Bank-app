package com.marwan.smartbank.di;

import android.content.Context;

import com.marwan.smartbank.data.local.database.AppDatabase;
import com.marwan.smartbank.data.local.dao.UserDao;
import com.marwan.smartbank.data.local.dao.TransactionDao;
import com.marwan.smartbank.data.local.dao.AccountDao;
import com.marwan.smartbank.data.local.dao.BeneficiaryDao;
import com.marwan.smartbank.data.local.preferences.PreferencesManager;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return AppDatabase.getInstance(context);
    }

    @Singleton
    @Provides
    public UserDao provideUserDao(AppDatabase database) {
        return database.userDao();
    }

    @Singleton
    @Provides
    public TransactionDao provideTransactionDao(AppDatabase database) {
        return database.transactionDao();
    }

    @Singleton
    @Provides
    public AccountDao provideAccountDao(AppDatabase database) {
        return database.accountDao();
    }

    @Singleton
    @Provides
    public BeneficiaryDao provideBeneficiaryDao(AppDatabase database) {
        return database.beneficiaryDao();
    }

    @Singleton
    @Provides
    public PreferencesManager providePreferencesManager(@ApplicationContext Context context) {
        return new PreferencesManager(context);
    }
}
