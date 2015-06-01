//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruhua.common.utils.encrypt.AESEncoder;
import org.apache.commons.lang.StringUtils;

public class CookieUtils {
    private static final String DEFAULT_ENCRYPT_KEY = "jily67nmrt346fvc";
    private String encryptKey = "jily67nmrt346fvc";

    public CookieUtils() {
    }

    public String getCookieValueWithoutDecrypt(HttpServletRequest servletRequest, String name) {
        return this.getCookieValue(servletRequest, name, false);
    }

    public String getCookieValue(HttpServletRequest servletRequest, String name) {
        return this.getCookieValue(servletRequest, name, true);
    }

    public String getCookieValue(HttpServletRequest servletRequest, String name, boolean needDecrypt) {
        Cookie[] cookies = servletRequest.getCookies();
        if(cookies != null && cookies.length > 0) {
            Cookie[] arr$ = cookies;
            int len$ = cookies.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Cookie cookie = arr$[i$];
                String cookieName = cookie.getName();
                if(cookieName.equals(name.trim())) {
                    String val = cookie.getValue();
                    if(StringUtils.isEmpty(val)) {
                        return null;
                    }

                    if(needDecrypt) {
                        val = this.decryptCookie(val);
                    }

                    return val;
                }
            }
        }

        return null;
    }

    public void deleteCookie(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String name) {
        Cookie cookie = new Cookie(name, (String)null);
        cookie.setMaxAge(0);
        String cookiePath = httpRequest.getContextPath();
        if(StringUtils.isBlank(cookiePath)) {
            cookiePath = "/";
        }

        cookie.setPath(cookiePath);
        httpResponse.addCookie(cookie);
    }

    public void addCookie(HttpServletResponse httpResponse, String name, String value, String path, int maxAge) {
        this.addCookie(httpResponse, name, value, path, maxAge, true);
    }

    private void addCookie(HttpServletResponse httpResponse, String name, String value, String path, int maxAge, boolean needEncrypt) {
        value = value == null?"":value;
        if(needEncrypt) {
            value = this.encryptCookie(value);
        }

        Cookie cookie = new Cookie(name, value);
        String cookiePath = path;
        if(StringUtils.isBlank(path)) {
            cookiePath = "/";
        }

        cookie.setPath(cookiePath);
        if(maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }

        httpResponse.addCookie(cookie);
    }

    public String encryptCookie(String cookie) {
        String enValue = null;

        try {
            enValue = AESEncoder.encrypt(cookie, this.encryptKey);
            return enValue;
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public String decryptCookie(String enCookie) {
        String deValue = null;

        try {
            deValue = AESEncoder.decrypt(enCookie, this.encryptKey);
            return deValue;
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public void addCookieWithoutEncrypt(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String name, String value, int maxAge) {
        String cookiePath = httpRequest.getContextPath();
        if(StringUtils.isBlank(cookiePath)) {
            cookiePath = "/";
        }

        this.addCookie(httpResponse, name, value, cookiePath, maxAge, false);
    }

    public void addCookie(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String name, String value, int maxAge) {
        String cookiePath = httpRequest.getContextPath();
        if(StringUtils.isBlank(cookiePath)) {
            cookiePath = "/";
        }

        this.addCookie((HttpServletResponse)httpResponse, (String)name, value, cookiePath, maxAge);
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }
}
