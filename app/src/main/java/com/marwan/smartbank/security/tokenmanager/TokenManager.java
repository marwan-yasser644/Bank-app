package com.marwan.smartbank.security.tokenmanager;

import com.marwan.smartbank.data.local.preferences.PreferencesManager;

public class TokenManager {
    private final PreferencesManager preferencesManager;

    public TokenManager(PreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    public void saveTokens(String accessToken, String refreshToken) {
        preferencesManager.setAccessToken(accessToken);
        preferencesManager.setRefreshToken(refreshToken);
    }

    public String getAccessToken() {
        return preferencesManager.getAccessToken();
    }

    public String getRefreshToken() {
        return preferencesManager.getRefreshToken();
    }

    public void clearTokens() {
        preferencesManager.setAccessToken("");
        preferencesManager.setRefreshToken("");
    }

    public boolean hasValidToken() {
        return !getAccessToken().isEmpty();
    }
}
