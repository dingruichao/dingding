//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.exception.resolver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruhua.common.utils.HttpServletUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class DefaultExceptionResolver extends SimpleMappingExceptionResolver {
    private AjaxRequestExceptionResolverResultStrategy ajaxRequestExceptionResolverResultStrategy = new DefaultAjaxRequestExceptionResolverResultStrategy();

    public DefaultExceptionResolver() {
    }

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return HttpServletUtils.isAjaxRequest(request)?this.ajaxRequestExceptionResolverResultStrategy.resolveResult(response, ex, 500, "系统内部错误", (String)null):super.doResolveException(request, response, handler, ex);
    }

    public void setAjaxRequestExceptionResolverResultStrategy(AjaxRequestExceptionResolverResultStrategy ajaxRequestExceptionResolverResultStrategy) {
        this.ajaxRequestExceptionResolverResultStrategy = ajaxRequestExceptionResolverResultStrategy;
    }
}
