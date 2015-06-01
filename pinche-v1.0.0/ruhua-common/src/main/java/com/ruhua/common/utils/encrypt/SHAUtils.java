//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public final class SHAUtils {
    private static final String SHA_ALGORITHM = "SHA-1";

    private SHAUtils() {
    }

    private static MessageDigest getSHADigestAlgorithm() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-1");
    }

    public static byte[] getSHADigest(byte[] source) throws NoSuchAlgorithmException {
        return getSHADigestAlgorithm().digest(source);
    }

    public static byte[] getSHADigest(String source) throws NoSuchAlgorithmException {
        return getSHADigest((byte[])source.getBytes());
    }

    public static String getSHADigestHex(byte[] source) throws NoSuchAlgorithmException {
        return new String(Hex.encodeHex(getSHADigest((byte[])source)));
    }

    public static String getSHADigestHex(String source) throws NoSuchAlgorithmException {
        return new String(Hex.encodeHex(getSHADigest((byte[])source.getBytes())));
    }

    public static String getSHADigestBase64(byte[] source) throws NoSuchAlgorithmException {
        return new String(Base64.encodeBase64(getSHADigest((byte[])source)));
    }

    public static String getSHADigestBase64(String source) throws NoSuchAlgorithmException {
        return new String(Base64.encodeBase64(getSHADigest((byte[])source.getBytes())));
    }
}
