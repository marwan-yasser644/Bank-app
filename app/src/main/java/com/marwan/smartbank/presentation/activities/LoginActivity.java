package com.marwan.smartbank.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModelProvider;

import com.marwan.smartbank.presentation.viewmodels.AuthViewModel;
import com.marwan.smartbank.databinding.ActivityLoginBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private AuthViewModel authViewModel;

    @Override
    protected ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        binding.nextButton.setOnClickListener(v -> handlePhoneSubmit());
        binding.biometricButton.setOnClickListener(v -> handleBiometricLogin());
    }

    @Override
    protected void setupObservers() {
        authViewModel.authState.observe(this, state -> {
            switch (state) {
                case LOADING:
                    showLoadingState();
                    break;
                case OTP_SENT:
                    showOtpField();
                    break;
                case AUTHENTICATED:
                    navigateToDashboard();
                    break;
                case ERROR:
                    hideLoadingState();
                    break;
            }
        });

        authViewModel.errorMessage.observe(this, error -> showError(error));
        authViewModel.successMessage.observe(this, message -> showSuccess(message));
    }

    private void handlePhoneSubmit() {
        String phone = binding.phoneInput.getText().toString().trim();

        if (phone.isEmpty()) {
            showError("Please enter phone number");
            return;
        }

        authViewModel.requestOtp(phone);
    }

    private void handleBiometricLogin() {
        // Biometric authentication implementation
        showSuccess("Biometric feature coming soon");
    }

    private void showOtpField() {
        binding.phoneInputContainer.setVisibility(View.GONE);
        binding.otpInputContainer.setVisibility(View.VISIBLE);
        binding.nextButton.setText("Verify OTP");
        binding.nextButton.setOnClickListener(v -> verifyOtp());
    }

    private void verifyOtp() {
        String phone = binding.phoneInput.getText().toString();
        String otp = binding.otpInput.getText().toString().trim();

        if (otp.isEmpty()) {
            showError("Please enter OTP");
            return;
        }

        authViewModel.verifyOtp(phone, otp);
    }

    private void showLoadingState() {
        binding.nextButton.setEnabled(false);
        binding.loadingIndicator.setVisibility(View.VISIBLE);
    }

    private void hideLoadingState() {
        binding.nextButton.setEnabled(true);
        binding.loadingIndicator.setVisibility(View.GONE);
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
