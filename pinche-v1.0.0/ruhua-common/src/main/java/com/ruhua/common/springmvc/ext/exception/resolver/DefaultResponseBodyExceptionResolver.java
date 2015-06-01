//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.ext.exception.resolver;


import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruhua.common.utils.HttpServletUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

public class DefaultResponseBodyExceptionResolver extends ResponseBodyExceptionResolver {
    public DefaultResponseBodyExceptionResolver() {
    }

    protected ModelAndView doResolve(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) throws Exception {
        return this.handleException(ex, request, response, handler);
    }

    protected ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception {
        short httpStatus = 500;
        String msg = "系统内部错误";
        HashMap returnData = new HashMap();
        returnData.put("httpStatus", Integer.valueOf(httpStatus));
        returnData.put("msg", msg);
        return HttpServletUtils.isAjaxRequest(request)?this.ajaxRequestExceptionResolverResultStrategy.resolveResult(response, ex, httpStatus, msg, (String)null):this.resolveByHttpMessageConverter(returnData, request, response);
    }
}
