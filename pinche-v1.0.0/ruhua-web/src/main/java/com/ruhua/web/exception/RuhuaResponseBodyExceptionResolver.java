package com.ruhua.web.exception;

//import com.jd.o2o.commons.domain.constants.ServiceResponseCode;
//import com.jd.o2o.commons.domain.response.ServiceResponse;
//import com.jd.o2o.commons.springmvc.ext.exception.resolver.ResponseBodyExceptionResolver;
import com.ruhua.common.springmvc.ext.exception.resolver.ResponseBodyExceptionResolver;
import com.ruhua.domain.common.base.constants.ServiceResponseCode;
import com.ruhua.domain.common.base.exception.O2OException;
import com.ruhua.domain.common.base.response.ServiceResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.jd.o2o.commons.utils.web.HttpServletUtils;
//import com.jd.o2o.lsp.domain.exception.O2OException;

/**
 * 基于ResponseBody注解返回值的异常分解器 O2O实现
 * User: lihuiyue
 * Date: 14-6-6
 * Time: 下午18:06
 * To change this template use File | Settings | File Templates.
 */
public class RuhuaResponseBodyExceptionResolver extends ResponseBodyExceptionResolver {

    /** {@inheritDoc} */
    protected ModelAndView doResolve(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) throws Exception {
        return handleException(ex, request, response, handler);
    }

    /**
     * 处理异常
     *
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    protected ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception {
        ServiceResponse serviceResponse = null;
        if (ex instanceof O2OException) {
            O2OException o2OException = (O2OException)ex;
            serviceResponse = new ServiceResponse(String.valueOf(o2OException.getCode()), o2OException.getMessage());
        } else {
            serviceResponse = new ServiceResponse(ServiceResponseCode.SYSTEM_ERROR);
        }
        if (isAjaxRequest(request)) {
            int httpStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            String msg = null;
            String code = null;
            if (ex instanceof O2OException) {
                httpStatus = HttpServletResponse.SC_OK;
                msg = serviceResponse.getMsg();
                code = serviceResponse.getCode();
            } else {
                msg = "系统内部错误";
            }
            return ajaxRequestExceptionResolverResultStrategy.resolveResult(response, ex, httpStatus, msg, code);
        }
        return resolveByHttpMessageConverter(serviceResponse, request, response);
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
