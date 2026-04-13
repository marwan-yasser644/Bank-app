package com.marwan.smartbank.utils.validators;

import android.os.Build;
import android.util.Patterns;

public class InputValidator {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        // Egyptian phone number validation
        return phoneNumber.matches("^(\\+20|0)(10|11|12|15)\\d{8}$");
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidOtp(String otp) {
        return otp != null && otp.matches("^\\d{6}$");
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0 && amount <= 999999.99;
    }

    public static boolean isValidIBAN(String iban) {
        if (iban == null || iban.isEmpty()) {
            return false;
        }
        return iban.matches("^(EG)\\d{29}$");
    }

    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && accountNumber.length() >= 10;
    }

    public static boolean isValidFullName(String fullName) {
        return fullName != null && fullName.trim().length() >= 3;
    }

    public static String getErrorMessage(String fieldName) {
        switch (fieldName) {
            case "phone":
                return "Invalid phone number";
            case "email":
                return "Invalid email address";
            case "otp":
                return "OTP must be 6 digits";
            case "amount":
                return "Invalid amount";
            case "iban":
                return "Invalid IBAN";
            default:
                return "Invalid input";
        }
    }
}
