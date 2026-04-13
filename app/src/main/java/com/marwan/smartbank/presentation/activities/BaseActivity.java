package com.marwan.smartbank.presentation.activities;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.marwan.smartbank.data.local.preferences.PreferencesManager;

import java.util.Locale;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected VB binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewBinding();
        setContentView(binding.getRoot());

        applyLocalization();
        setupUI();
        setupObservers();
    }

    protected void applyLocalization() {
        String language = getPreferencesManager().getLanguage();

        if (language.equals("ar")) {
            setAppLocale(new Locale("ar", "EG"));
        } else {
            setAppLocale(new Locale("en", "US"));
        }
    }

    protected void setAppLocale(Locale locale) {
        Locale.setDefault(locale);

        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }

        resources.updateConfiguration(config, dm);
    }

    protected PreferencesManager getPreferencesManager() {
        return new PreferencesManager(this);
    }

    protected abstract VB getViewBinding();

    protected abstract void setupUI();

    protected abstract void setupObservers();

    protected void showError(String message) {
        // Show error snackbar or toast
    }

    protected void showSuccess(String message) {
        // Show success snackbar or toast
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
