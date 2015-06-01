//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.interceptor;

//import com.jd.o2o.commons.springmvc.annotation.Token;
//import com.jd.o2o.commons.utils.web.HttpServletUtils;
//import com.jd.o2o.commons.utils.web.cookie.CookieUtils;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruhua.common.springmvc.annotation.Token;
import com.ruhua.common.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TokenInterceptor extends HandlerInterceptorAdapter {
    private static final String TOKEN_ATTRIBUTE_NAME = "prevent_ds_token";
    private static final String TOKEN_COOKIE_NAME = "prevent_ds_token";
    private static final String TOKEN_PARAMETER_NAME = "prevent_ds_token";
    protected static final String ERROR_MESSAGE = "错误请求或Token丢失，请刷新页面后再进行此操作!";
    private static final Integer TOKEN_AGE = Integer.valueOf(-1);
    private CookieUtils cookieUtils;

    public TokenInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        } else {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            Token annotation = (Token)method.getAnnotation(Token.class);
            if(annotation == null) {
                return true;
            } else {
                String token = null;
                if(annotation.generate()) {
                    token = this.generateToken();
                    this.cookieUtils.addCookieWithoutEncrypt(request, response, "prevent_ds_token", token, TOKEN_AGE.intValue());
                    request.setAttribute("prevent_ds_token", token);
                }

                if(annotation.valid()) {
                    token = this.cookieUtils.getCookieValueWithoutDecrypt(request, "prevent_ds_token");
                    boolean result = false;
                    if(token != null && token.equals(request.getParameter("prevent_ds_token"))) {
                        result = true;
                    }

                    if(!result) {
                        this.invalidHandle(request, response);
                    }

                    if(token != null && !annotation.generate()) {
                        this.cookieUtils.deleteCookie(request, response, "prevent_ds_token");
                    }

                    return result;
                } else {
                    return true;
                }
            }
        }
    }

    protected void invalidHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(isAjaxRequest(request)) {
            response.addHeader("tokenStatus", "invalid");
            response.addHeader("errorMsg", "错误请求或Token丢失，请刷新页面后再进行此操作!");
        } else {
            request.setAttribute("errorMsg", "错误请求或Token丢失，请刷新页面后再进行此操作!");
            request.setAttribute("tokenStatus", "invalid");
            response.sendError(400, "错误请求或Token丢失，请刷新页面后再进行此操作!");
        }

    }

    private String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public void setCookieUtils(CookieUtils cookieUtils) {
        this.cookieUtils = cookieUtils;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String key = "X-Requested-With";
        String ajaxRequest = request.getHeader(key);
        if(StringUtils.isEmpty(ajaxRequest)) {
            ajaxRequest = request.getParameter(key);
        }

        return StringUtils.isNotEmpty(ajaxRequest)?ajaxRequest.equals("XMLHttpRequest"):false;
    }
}
