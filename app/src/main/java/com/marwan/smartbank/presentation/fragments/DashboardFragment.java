package com.marwan.smartbank.presentation.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.marwan.smartbank.presentation.adapters.TransactionAdapter;
import com.marwan.smartbank.presentation.viewmodels.DashboardViewModel;
import com.marwan.smartbank.databinding.FragmentDashboardBinding;
import com.marwan.smartbank.utils.helpers.FormatHelper;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragment extends BaseFragment<FragmentDashboardBinding> {

    private DashboardViewModel viewModel;
    private TransactionAdapter adapter;

    @Override
    protected FragmentDashboardBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentDashboardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        setupRecyclerView();
        setupClickListeners();
    }

    private void setupRecyclerView() {
        adapter = new TransactionAdapter(getContext(), transaction -> {
            // Handle transaction click
        });

        binding.transactionList.setAdapter(adapter);
        binding.transactionList.setLayoutManager(
                new LinearLayoutManager(getContext()));
    }

    private void setupClickListeners() {
        binding.sendMoneyButton.setOnClickListener(v -> navigateToSendMoney());
        binding.billPaymentButton.setOnClickListener(v -> navigateToBillPayment());
        binding.balanceToggle.setOnClickListener(v -> toggleBalanceVisibility());
        binding.refreshButton.setOnClickListener(v -> viewModel.refreshData());
    }

    @Override
    protected void setupObservers() {
        viewModel.userAccounts.observe(getViewLifecycleOwner(), accounts -> {
            if (!accounts.isEmpty()) {
                binding.balanceAmount.setText(
                        FormatHelper.formatCurrency(accounts.get(0).getBalance()));
            }
        });

        viewModel.recentTransactions.observe(getViewLifecycleOwner(), transactions -> {
            adapter.setTransactions(transactions);
        });

        viewModel.isLoading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                showLoading(true);
            } else {
                showLoading(false);
            }
        });

        viewModel.errorMessage.observe(getViewLifecycleOwner(), error -> {
            showError(error);
        });
    }

    private void navigateToSendMoney() {
        // Navigate to send money fragment
    }

    private void navigateToBillPayment() {
        // Navigate to bill payment fragment
    }

    private void toggleBalanceVisibility() {
        // Toggle balance visibility
    }
}
