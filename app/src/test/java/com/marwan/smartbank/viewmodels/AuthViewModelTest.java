package com.marwan.smartbank.presentation.viewmodels;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.marwan.smartbank.domain.repositories.AuthRepository;
import com.marwan.smartbank.data.remote.responses.OtpResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.rxjava3.core.Single;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private AuthRepository authRepository;

    @Mock
    private Observer<String> errorObserver;

    private AuthViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new AuthViewModel(authRepository);
    }

    @Test
    public void testRequestOtp_InvalidPhone() {
        viewModel.requestOtp("");

        viewModel.errorMessage.observeForever(errorObserver);
        verify(errorObserver).onChanged("Invalid phone number");
    }

    @Test
    public void testRequestOtp_ValidPhone() {
        String phoneNumber = "+201001234567";
        OtpResponse mockResponse = new OtpResponse(true, "OTP sent", "otp123", 120);

        when(authRepository.requestOtp(phoneNumber))
                .thenReturn(Single.just(mockResponse));

        viewModel.requestOtp(phoneNumber);

        verify(authRepository).requestOtp(phoneNumber);
    }
}
