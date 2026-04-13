package com.marwan.smartbank.utils.validators;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputValidatorTest {

    @Test
    public void testIsValidPhoneNumber_ValidEgyptianPhone() {
        assertTrue(InputValidator.isValidPhoneNumber("+201001234567"));
        assertTrue(InputValidator.isValidPhoneNumber("01001234567"));
    }

    @Test
    public void testIsValidPhoneNumber_InvalidPhone() {
        assertFalse(InputValidator.isValidPhoneNumber(""));
        assertFalse(InputValidator.isValidPhoneNumber("123"));
        assertFalse(InputValidator.isValidPhoneNumber("invalid"));
    }

    @Test
    public void testIsValidEmail_ValidEmail() {
        assertTrue(InputValidator.isValidEmail("test@example.com"));
        assertTrue(InputValidator.isValidEmail("user.name@test.co.uk"));
    }

    @Test
    public void testIsValidEmail_InvalidEmail() {
        assertFalse(InputValidator.isValidEmail(""));
        assertFalse(InputValidator.isValidEmail("invalid.email"));
        assertFalse(InputValidator.isValidEmail("@example.com"));
    }

    @Test
    public void testIsValidOtp_ValidOtp() {
        assertTrue(InputValidator.isValidOtp("123456"));
        assertTrue(InputValidator.isValidOtp("000000"));
    }

    @Test
    public void testIsValidOtp_InvalidOtp() {
        assertFalse(InputValidator.isValidOtp(""));
        assertFalse(InputValidator.isValidOtp("12345"));
        assertFalse(InputValidator.isValidOtp("1234567"));
        assertFalse(InputValidator.isValidOtp("abcdef"));
    }

    @Test
    public void testIsValidAmount_ValidAmount() {
        assertTrue(InputValidator.isValidAmount(100.0));
        assertTrue(InputValidator.isValidAmount(0.01));
        assertTrue(InputValidator.isValidAmount(999999.99));
    }

    @Test
    public void testIsValidAmount_InvalidAmount() {
        assertFalse(InputValidator.isValidAmount(0));
        assertFalse(InputValidator.isValidAmount(-100));
        assertFalse(InputValidator.isValidAmount(1000000));
    }
}
