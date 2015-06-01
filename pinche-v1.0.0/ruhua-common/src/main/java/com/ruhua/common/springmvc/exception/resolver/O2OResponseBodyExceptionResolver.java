//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.exception.resolver;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruhua.common.springmvc.ext.exception.resolver.ResponseBodyExceptionResolver;
import com.ruhua.common.utils.HttpServletUtils;
import com.ruhua.domain.common.base.constants.ServiceResponseCode;
import com.ruhua.domain.common.base.exception.O2OException;
import com.ruhua.domain.common.base.response.ServiceResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

public class O2OResponseBodyExceptionResolver extends ResponseBodyExceptionResolver {
    public O2OResponseBodyExceptionResolver() {
    }

    protected ModelAndView doResolve(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) throws Exception {
        return this.handleException(ex, request, response, handler);
    }

    protected ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception {
        ServiceResponse serviceResponse = null;
        if(ex instanceof O2OException) {
            O2OException httpStatus = (O2OException)ex;
            serviceResponse = new ServiceResponse(httpStatus.getCode(), httpStatus.getMessage());
        } else {
            serviceResponse = new ServiceResponse(ServiceResponseCode.SYSTEM_ERROR);
        }

        if(HttpServletUtils.isAjaxRequest(request)) {
            short httpStatus1 = 500;
            String msg = null;
            String code = null;
            if(ex instanceof O2OException) {
                httpStatus1 = 200;
                msg = serviceResponse.getMsg();
                code = serviceResponse.getCode();
            } else {
                msg = "系统内部错误";
            }

            return this.ajaxRequestExceptionResolverResultStrategy.resolveResult(response, ex, httpStatus1, msg, code);
        } else {
            return this.resolveByHttpMessageConverter(serviceResponse, request, response);
        }
    }
}
