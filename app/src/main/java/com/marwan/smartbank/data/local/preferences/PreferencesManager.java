package com.marwan.smartbank.data.local.preferences;

import android.content.Context;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class PreferencesManager {
    private final EncryptedSharedPreferences prefs;
    private static final String LANGUAGE_KEY = "language";
    private static final String THEME_KEY = "theme";
    private static final String FIRST_RUN_KEY = "first_run";
    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String REFRESH_TOKEN_KEY = "refresh_token";

    public PreferencesManager(Context context) {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();

            this.prefs = EncryptedSharedPreferences.create(
                context,
                "smartbank_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLanguage(String languageCode) {
        prefs.edit().putString(LANGUAGE_KEY, languageCode).apply();
    }

    public String getLanguage() {
        return prefs.getString(LANGUAGE_KEY, "en");
    }

    public void setTheme(String theme) {
        prefs.edit().putString(THEME_KEY, theme).apply();
    }

    public String getTheme() {
        return prefs.getString(THEME_KEY, "light");
    }

    public void setAccessToken(String token) {
        prefs.edit().putString(ACCESS_TOKEN_KEY, token).apply();
    }

    public String getAccessToken() {
        return prefs.getString(ACCESS_TOKEN_KEY, "");
    }

    public void setRefreshToken(String token) {
        prefs.edit().putString(REFRESH_TOKEN_KEY, token).apply();
    }

    public String getRefreshToken() {
        return prefs.getString(REFRESH_TOKEN_KEY, "");
    }

    public boolean isFirstRun() {
        boolean isFirstRun = prefs.getBoolean(FIRST_RUN_KEY, true);
        if (isFirstRun) {
            prefs.edit().putBoolean(FIRST_RUN_KEY, false).apply();
        }
        return isFirstRun;
    }

    public void clearAllData() {
        prefs.edit().clear().apply();
    }
}
