package com.marwan.smartbank.utils.helpers;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatHelper {

    public static String formatCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("ar", "EG"));
        return currencyFormatter.format(amount);
    }

    public static String formatCurrencySimple(double amount) {
        return String.format(Locale.US, "%.2f", amount);
    }

    public static String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public static String formatDateTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 10) {
            return phoneNumber;
        }
        return phoneNumber.substring(0, phoneNumber.length() - 4).replaceAll("\\d", "*")
                + phoneNumber.substring(phoneNumber.length() - 4);
    }

    public static String formatAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) {
            return accountNumber;
        }
        return "****" + accountNumber.substring(accountNumber.length() - 4);
    }

    public static String maskSensitiveData(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        int visibleChars = Math.min(2, data.length());
        StringBuilder masked = new StringBuilder();
        for (int i = 0; i < data.length() - visibleChars; i++) {
            masked.append("*");
        }
        masked.append(data.substring(data.length() - visibleChars));
        return masked.toString();
    }
}
