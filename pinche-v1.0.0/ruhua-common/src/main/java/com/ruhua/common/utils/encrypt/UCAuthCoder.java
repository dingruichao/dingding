//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.utils.encrypt;

import com.ruhua.common.utils.encoding.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UCAuthCoder {
    private static final String DEFAULT_KEY = "12345qwert";

    public UCAuthCoder() {
    }

    public String coder(String content, String operation, String key, int expiry) {
        if(operation.equals("DECODE")) {
            content = this.decodeBase64Param(content);
        }

        byte ckey_length = 4;
        key = this.md5(key != null?key:"12345qwert");
        String keya = this.md5(this.subStr(key, 0, 16));
        String keyb = this.md5(this.subStr(key, 16, 16));
        String keyc = ckey_length > 0?(operation.equals("DECODE")?this.subStr(content, 0, ckey_length):this.subStr(this.md5(this.microTime()), -ckey_length)):"";
        String cryptkey = keya + this.md5(keya + keyc);
        int key_length = cryptkey.length();
        content = operation.equals("DECODE")?this.base64Decode(this.subStr(content, ckey_length)):this.sprintf("%010d", expiry > 0?(long)expiry + this.time():0L) + this.subStr(this.md5(content + keyb), 0, 16) + content;
        int string_length = content.length();
        StringBuffer result1 = new StringBuffer();
        int[] box = new int[256];

        for(int rndkey = 0; rndkey < 256; box[rndkey] = rndkey++) {
            ;
        }

        int[] var19 = new int[256];

        int j;
        for(j = 0; j <= 255; ++j) {
            var19[j] = cryptkey.charAt(j % key_length);
        }

        j = 0;

        int a;
        int result;
        for(a = 0; a < 256; ++a) {
            j = (j + box[a] + var19[a]) % 256;
            result = box[a];
            box[a] = box[j];
            box[j] = result;
        }

        j = 0;
        a = 0;

        for(result = 0; result < string_length; ++result) {
            a = (a + 1) % 256;
            j = (j + box[a]) % 256;
            int tmp = box[a];
            box[a] = box[j];
            box[j] = tmp;
            result1.append((char)(content.charAt(result) ^ box[(box[a] + box[j]) % 256]));
        }

        if(!operation.equals("DECODE")) {
            return this.encodeBase64Param(keyc + this.base64Encode(result1.toString()).replaceAll("=", ""));
        } else {
            String var20 = result1.substring(0, result1.length());
            return (Integer.parseInt(this.subStr(var20.toString(), 0, 10)) == 0 || Long.parseLong(this.subStr(var20.toString(), 0, 10)) - this.time() > 0L) && this.subStr(var20.toString(), 10, 16).equals(this.subStr(this.md5(this.subStr(var20.toString(), 26) + keyb), 0, 16))?this.subStr(var20.toString(), 26):"";
        }
    }

    private String encodeBase64Param(String base64Param) {
        return base64Param != null && base64Param.trim().length() != 0?base64Param.replace("+", "*").replace("/", "-").replace("=", "."):base64Param;
    }

    private String decodeBase64Param(String base64Param) {
        return base64Param != null && base64Param.trim().length() != 0?base64Param.replace("*", "+").replace("-", "/").replace(".", "="):base64Param;
    }

    private String base64Encode(String input) {
        try {
            return new String(Base64.encode(input.getBytes("iso-8859-1")));
        } catch (Exception var3) {
            return var3.getMessage();
        }
    }

    private String base64Decode(String input) {
        try {
            return new String(Base64.decode(input.toCharArray()), "iso-8859-1");
        } catch (Exception var3) {
            return var3.getMessage();
        }
    }

    private String md5(String input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
            return null;
        }

        return this.byte2Hex(md.digest(input.getBytes()));
    }

    private String md5(long input) {
        return this.md5(String.valueOf(input));
    }

    private String byte2Hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";

        for(int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if(stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }

        return hs.toString();
    }

    private String subStr(String input, int begin, int length) {
        return input.substring(begin, begin + length);
    }

    private String subStr(String input, int begin) {
        return begin > 0?input.substring(begin):input.substring(input.length() + begin);
    }

    private long microTime() {
        return System.currentTimeMillis();
    }

    private long time() {
        return System.currentTimeMillis() / 1000L;
    }

    private String sprintf(String format, long input) {
        String temp = "0000000000" + input;
        return temp.substring(temp.length() - 10);
    }

    public static void main(String[] args) {
        String code = "0b60CbDhFwRgod3WEgHN4IBvIZ35sp1cjhykQylxpeGpXwNf6TZfYTXBF9pdR9US9CJqG*pv0Zo7kJcSOeFDuUs";
        System.out.println((new UCAuthCoder()).coder(code, "DECODE", "123456", 0));
        String param = "syncAction=syncLogin&loginName=zjjie";
        String encodeString = (new UCAuthCoder()).coder(param, "ENCODE", "123456", 0);
        System.out.println("encodeString = " + encodeString);
        String decodeString = (new UCAuthCoder()).coder(encodeString, "DECODE", "123456", 0);
        System.out.println("decodeString = " + decodeString);
    }
}
