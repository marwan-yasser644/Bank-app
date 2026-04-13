package com.marwan.smartbank.security.biometric;

import android.os.Handler;
import android.os.Looper;

import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.Executor;

public class BiometricAuthManager {
    private final FragmentActivity activity;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    public interface BiometricAuthCallback {
        void onSuccess();
        void onError(String errorMessage);
    }

    public BiometricAuthManager(FragmentActivity activity) {
        this.activity = activity;
        initializeBiometric();
    }

    private void initializeBiometric() {
        Executor executor = new Executor() {
            final Handler handler = new Handler(Looper.getMainLooper());
            @Override
            public void execute(Runnable command) {
                handler.post(command);
            }
        };

        biometricPrompt = new BiometricPrompt(activity, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(
                            BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                    }

                    @Override
                    public void onAuthenticationError(
                            int errorCode, CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                    }
                });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Login")
                .setSubtitle("Use your fingerprint or face to login")
                .setNegativeButtonText("Cancel")
                .build();
    }

    public void authenticateWithBiometric(BiometricAuthCallback callback) {
        biometricPrompt.authenticate(promptInfo);
    }

    public boolean isBiometricAvailable() {
        try {
            BiometricPrompt.BiometricManager manager =
                    BiometricPrompt.BiometricManager.from(activity);
            return manager.canAuthenticate(
                    BiometricPrompt.BiometricManager.BIOMETRIC_STRONG) ==
                    BiometricPrompt.BiometricManager.BIOMETRIC_SUCCESS;
        } catch (Exception e) {
            return false;
        }
    }
}
