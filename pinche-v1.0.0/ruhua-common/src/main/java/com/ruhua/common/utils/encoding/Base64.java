//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.utils.encoding;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class Base64 {
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] codes = new byte[256];

    public Base64() {
    }

    public static char[] encode(byte[] data) {
        char[] out = new char[(data.length + 2) / 3 * 4];
        int i = 0;

        for(int index = 0; i < data.length; index += 4) {
            boolean quad = false;
            boolean trip = false;
            int val = 255 & data[i];
            val <<= 8;
            if(i + 1 < data.length) {
                val |= 255 & data[i + 1];
                trip = true;
            }

            val <<= 8;
            if(i + 2 < data.length) {
                val |= 255 & data[i + 2];
                quad = true;
            }

            out[index + 3] = alphabet[quad?val & 63:64];
            val >>= 6;
            out[index + 2] = alphabet[trip?val & 63:64];
            val >>= 6;
            out[index + 1] = alphabet[val & 63];
            val >>= 6;
            out[index + 0] = alphabet[val & 63];
            i += 3;
        }

        return out;
    }

    public static byte[] decode(char[] data) {
        int tempLen = data.length;

        int len;
        for(len = 0; len < data.length; ++len) {
            if(data[len] > 255 || codes[data[len]] < 0) {
                --tempLen;
            }
        }

        len = tempLen / 4 * 3;
        if(tempLen % 4 == 3) {
            len += 2;
        }

        if(tempLen % 4 == 2) {
            ++len;
        }

        byte[] out = new byte[len];
        int shift = 0;
        int accum = 0;
        int index = 0;

        for(int ix = 0; ix < data.length; ++ix) {
            byte value = data[ix] > 255?-1:codes[data[ix]];
            if(value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if(shift >= 8) {
                    shift -= 8;
                    out[index++] = (byte)(accum >> shift & 255);
                }
            }
        }

        if(index != out.length) {
            throw new Error("Miscalculated data length (wrote " + index + " instead of " + out.length + ")");
        } else {
            return out;
        }
    }

    public static void main(String[] args) {
        boolean decode = false;
        if(args.length == 0) {
            System.out.println("usage:  java Base64 [-d[ecode]] filename");
            System.exit(0);
        }

        for(int filename = 0; filename < args.length; ++filename) {
            if("-decode".equalsIgnoreCase(args[filename])) {
                decode = true;
            } else if("-d".equalsIgnoreCase(args[filename])) {
                decode = true;
            }
        }

        String var6 = args[args.length - 1];
        File file = new File(var6);
        if(!file.exists()) {
            System.out.println("Error:  file \'" + var6 + "\' doesn\'t exist!");
            System.exit(0);
        }

        if(decode) {
            char[] decoded = readChars(file);
            byte[] encoded = decode(decoded);
            writeBytes(file, encoded);
        } else {
            byte[] var7 = readBytes(file);
            char[] var8 = encode(var7);
            writeChars(file, var8);
        }

    }

    private static byte[] readBytes(File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            FileInputStream e = new FileInputStream(file);
            BufferedInputStream is = new BufferedInputStream(e);
            boolean count = false;
            byte[] buf = new byte[16384];

            int count1;
            while((count1 = is.read(buf)) != -1) {
                if(count1 > 0) {
                    baos.write(buf, 0, count1);
                }
            }

            is.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return baos.toByteArray();
    }

    private static char[] readChars(File file) {
        CharArrayWriter caw = new CharArrayWriter();

        try {
            FileReader e = new FileReader(file);
            BufferedReader in = new BufferedReader(e);
            boolean count = false;
            char[] buf = new char[16384];

            int count1;
            while((count1 = in.read(buf)) != -1) {
                if(count1 > 0) {
                    caw.write(buf, 0, count1);
                }
            }

            in.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return caw.toCharArray();
    }

    private static void writeBytes(File file, byte[] data) {
        try {
            FileOutputStream e = new FileOutputStream(file);
            BufferedOutputStream os = new BufferedOutputStream(e);
            os.write(data);
            os.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    private static void writeChars(File file, char[] data) {
        try {
            FileWriter e = new FileWriter(file);
            BufferedWriter os = new BufferedWriter(e);
            os.write(data);
            os.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    static {
        int i;
        for(i = 0; i < 256; ++i) {
            codes[i] = -1;
        }

        for(i = 65; i <= 90; ++i) {
            codes[i] = (byte)(i - 65);
        }

        for(i = 97; i <= 122; ++i) {
            codes[i] = (byte)(26 + i - 97);
        }

        for(i = 48; i <= 57; ++i) {
            codes[i] = (byte)(52 + i - 48);
        }

        codes[43] = 62;
        codes[47] = 63;
    }
}
