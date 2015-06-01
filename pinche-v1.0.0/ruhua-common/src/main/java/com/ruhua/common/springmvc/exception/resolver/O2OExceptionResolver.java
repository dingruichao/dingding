//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.exception.resolver;


//import com.jd.o2o.commons.utils.web.HttpServletUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruhua.common.utils.HttpServletUtils;
import com.ruhua.domain.common.base.exception.O2OException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class O2OExceptionResolver extends SimpleMappingExceptionResolver {
    private AjaxRequestExceptionResolverResultStrategy ajaxRequestExceptionResolverResultStrategy = new DefaultAjaxRequestExceptionResolverResultStrategy();

    public O2OExceptionResolver() {
    }

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if(!(ex instanceof O2OException)) {
            return null;
        } else {
            O2OException o2OException = (O2OException)ex;
            this.logException(o2OException);
            return HttpServletUtils.isAjaxRequest(request)?this.ajaxRequestExceptionResolverResultStrategy.resolveResult(response, ex, 200, o2OException.getMessage(),
                    o2OException.getCode()):super.doResolveException(request, response, handler, ex);
        }
    }

    private void logException(O2OException ex) {
        if(this.logger.isWarnEnabled()) {
            this.logger.warn(ex.toString(), ex);
        }

    }

    public void setAjaxRequestExceptionResolverResultStrategy(AjaxRequestExceptionResolverResultStrategy ajaxRequestExceptionResolverResultStrategy) {
        this.ajaxRequestExceptionResolverResultStrategy = ajaxRequestExceptionResolverResultStrategy;
    }
}
