package com.marwan.smartbank.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    protected VB binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        binding = getViewBinding(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            View view,
            Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupUI();
        setupObservers();
    }

    protected abstract VB getViewBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void setupUI();

    protected abstract void setupObservers();

    protected void showError(String message) {
        // Show error snackbar or toast
    }

    protected void showSuccess(String message) {
        // Show success snackbar or toast
    }

    protected void showLoading(boolean isLoading) {
        // Show/hide loading indicator
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
