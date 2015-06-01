//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.exception.resolver;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface AjaxRequestExceptionResolverResultStrategy {
    ModelAndView resolveResult(HttpServletResponse var1, Exception var2, int var3, String var4, String var5);
}
