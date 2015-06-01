package com.ruhua.web.exception;

//import com.jd.o2o.commons.domain.constants.ServiceResponseCode;
//import com.jd.o2o.commons.domain.response.ServiceResponse;
//import com.jd.o2o.commons.springmvc.ext.exception.resolver.ResponseBodyExceptionResolver;
import com.ruhua.common.springmvc.ext.exception.resolver.ResponseBodyExceptionResolver;
import com.ruhua.domain.common.base.constants.ServiceResponseCode;
import com.ruhua.domain.common.base.response.ServiceResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全异常分解器
 * User: lihuiyue
 * Date: 14-6-6
 * Time: 下午18:06
 * To change this template use File | Settings | File Templates.
 */
public class SecurityExceptionResolver extends ResponseBodyExceptionResolver {


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
//        if (!(ex instanceof com.jd.josl.privilege.security.exception.SecurityException)) {
//            return null;
//        }

        serviceResponse = new ServiceResponse(ServiceResponseCode.USER_NOT_LOGIN);
        if (isAjaxRequest(request)) {
            int httpStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            String msg = serviceResponse.getMsg();
            String code = serviceResponse.getCode();
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
