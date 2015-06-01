//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.ruhua.common.utils.encoding.Base32;
import org.apache.commons.lang.StringUtils;

public final class AESEncoder {
    private static final String ENCRYPT_TYPE = "AES";
    private static final String RAW_KEY = "ASCII";
    public static final String O2O_SECRET_KEY = "vuxKv6A1OoB2TTt5";

    private AESEncoder() {
    }

    public static String decrypt(String src) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(src, "vuxKv6A1OoB2TTt5");
    }

    public static String decrypt(String src, String key) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if(!StringUtils.isBlank(src) && !StringUtils.isBlank(key)) {
            if(key.length() != 16) {
                throw new IllegalArgumentException("密钥长度必须为16位");
            } else {
                byte[] raw = key.getBytes("ASCII");
                SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(2, secretKeySpec);
                byte[] encrypted = decoding(src);
                byte[] original = cipher.doFinal(encrypted);
                return new String(original);
            }
        } else {
            throw new IllegalArgumentException("src or key must not be empty");
        }
    }

    public static String encrypt(String src) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return encrypt(src, "vuxKv6A1OoB2TTt5");
    }

    public static String encrypt(String src, String key) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if(!StringUtils.isBlank(src) && !StringUtils.isBlank(key)) {
            if(key.length() != 16) {
                throw new IllegalArgumentException("密钥长度必须为16位");
            } else {
                byte[] raw = key.getBytes("ASCII");
                SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(1, keySpec);
                byte[] encrypted = cipher.doFinal(src.getBytes());
                return encoding(encrypted);
            }
        } else {
            throw new IllegalArgumentException("src or key must not be empty");
        }
    }

    private static String encoding(byte[] bytes) {
        return Base32.encode(bytes);
    }

    private static byte[] decoding(String value) {
        return Base32.decode(value);
    }
}
