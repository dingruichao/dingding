//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.exception.resolver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class DefaultAjaxRequestExceptionResolverResultStrategy implements AjaxRequestExceptionResolverResultStrategy {
    private static final Logger logger = Logger.getLogger(DefaultAjaxRequestExceptionResolverResultStrategy.class);

    public DefaultAjaxRequestExceptionResolverResultStrategy() {
    }

    public ModelAndView resolveResult(HttpServletResponse response, Exception ex, int httpStatus, String msg, String code) {
        response.setHeader("httpStatus", (new Integer(httpStatus)).toString());
        if(StringUtils.isNotBlank(code)) {
            response.setHeader("code", code);
        }

        if(StringUtils.isNotBlank(msg)) {
            String encodeMsg = null;

            try {
                encodeMsg = URLEncoder.encode(msg, "UTF-8");
            } catch (UnsupportedEncodingException var8) {
                logger.error("DefaultAjaxRequestExceptionResolverResultStrategy->resolverResult error", var8);
            }

            response.setHeader("msg", encodeMsg);
        }

        return new ModelAndView();
    }
}
