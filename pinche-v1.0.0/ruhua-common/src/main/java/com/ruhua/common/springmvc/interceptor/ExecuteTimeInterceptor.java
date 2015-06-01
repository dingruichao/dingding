//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger(ExecuteTimeInterceptor.class);
    private static final String START_TIME_ATTRIBUTE_NAME = ExecuteTimeInterceptor.class.getName() + ".START_TIME";
    public static final String EXECUTE_TIME_ATTRIBUTE_NAME = ExecuteTimeInterceptor.class.getName() + ".EXECUTE_TIME";
    private static final String REDIRECT_VIEW_NAME_PREFIX = "redirect:";

    public ExecuteTimeInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long startTime = (Long)request.getAttribute(START_TIME_ATTRIBUTE_NAME);
        if(startTime == null) {
            startTime = Long.valueOf(System.currentTimeMillis());
            request.setAttribute(START_TIME_ATTRIBUTE_NAME, startTime);
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long executeTime = (Long)request.getAttribute(EXECUTE_TIME_ATTRIBUTE_NAME);
        if(executeTime == null) {
            Long viewName = (Long)request.getAttribute(START_TIME_ATTRIBUTE_NAME);
            Long endTime = Long.valueOf(System.currentTimeMillis());
            executeTime = Long.valueOf(endTime.longValue() - viewName.longValue());
            request.setAttribute(START_TIME_ATTRIBUTE_NAME, viewName);
        }

        if(modelAndView != null) {
            String viewName1 = modelAndView.getViewName();
            if(!StringUtils.startsWith(viewName1, "redirect:")) {
                modelAndView.addObject(EXECUTE_TIME_ATTRIBUTE_NAME, executeTime);
            }
        }

        if(logger.isDebugEnabled()) {
            logger.debug("[" + handler + "] executeTime: " + executeTime + "ms");
        }

    }
}
