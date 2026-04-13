package com.marwan.smartbank.security.encryption;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyStore;
import java.util.Base64;

public class EncryptionManager {
    private static final String CIPHER_MODE = "AES/GCM/NoPadding";
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String KEY_ALIAS = "smartbank_key";
    private Cipher cipher;
    private SecretKey secretKey;

    public EncryptionManager() {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
            keyStore.load(null);

            if (!keyStore.containsAlias(KEY_ALIAS)) {
                createKey();
            }

            SecretKey key = (SecretKey) keyStore.getKey(KEY_ALIAS, null);
            cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            this.secretKey = key;
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize encryption", e);
        }
    }

    public String encryptData(String data) {
        try {
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            byte[] iv = cipher.getIV();
            return Base64.getEncoder().encodeToString(concatenate(iv, encryptedData));
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt data", e);
        }
    }

    public String decryptData(String encryptedData) {
        try {
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);
            byte[] iv = new byte[12];
            System.arraycopy(decodedData, 0, iv, 0, 12);

            Cipher decipher = Cipher.getInstance(CIPHER_MODE);
            decipher.init(Cipher.DECRYPT_MODE, secretKey,
                    new javax.crypto.spec.GCMParameterSpec(128, iv));

            byte[] decryptedData = decipher.doFinal(decodedData, 12, decodedData.length - 12);
            return new String(decryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt data", e);
        }
    }

    private void createKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE);

            keyGenerator.init(
                    new KeyGenParameterSpec.Builder(
                            KEY_ALIAS,
                            KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                            .build());

            keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create encryption key", e);
        }
    }

    private byte[] concatenate(byte[] prefix, byte[] suffix) {
        byte[] result = new byte[prefix.length + suffix.length];
        System.arraycopy(prefix, 0, result, 0, prefix.length);
        System.arraycopy(suffix, 0, result, prefix.length, suffix.length);
        return result;
    }
}
