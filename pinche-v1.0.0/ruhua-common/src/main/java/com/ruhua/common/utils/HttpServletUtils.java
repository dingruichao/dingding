//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class HttpServletUtils extends ServletUtils {
    private static final Logger logger = Logger.getLogger(HttpServletUtils.class);
    public static final String DEFAULT_ENCODING = "UTF-8";

    public HttpServletUtils() {
    }

    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    public static ServletContext getServletContext(HttpServletRequest request) {
        return request.getSession().getServletContext();
    }

    public static String getRealPath(HttpServletRequest request, String path) {
        return getServletContext(request).getRealPath(path);
    }

    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        HashMap result = new HashMap();
        Iterator iterator = request.getParameterMap().entrySet().iterator();

        while(iterator.hasNext()) {
            Entry entry = (Entry)iterator.next();
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            result.put(key, value);
        }

        return result;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String key = "X-Requested-With";
        String ajaxRequest = request.getHeader(key);
        if(StringUtils.isEmpty(ajaxRequest)) {
            ajaxRequest = request.getParameter(key);
        }

        return StringUtils.isNotEmpty(ajaxRequest)?ajaxRequest.equals("XMLHttpRequest"):false;
    }

    public static void outString(HttpServletResponse response, String str) {
        PrintWriter out = null;

        try {
            out = response.getWriter();
            out.write(str);
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            if(out != null) {
                out.flush();
                out.close();
            }

        }

    }

    public static HttpServletUtils.BrowserInfo detectBrowser(HttpServletRequest request) {
        return new HttpServletUtils.BrowserInfo(request);
    }

    public static class BrowserInfo {
        private String browser;
        private String version;

        public BrowserInfo(HttpServletRequest request) {
            String browserType = request.getHeader("User-Agent").toLowerCase();
            if(browserType != null) {
                if(browserType.indexOf("msie") != -1) {
                    this.browser = "ie";
                    String tempStr = browserType.substring(browserType.indexOf("msie"), browserType.length());
                    this.version = tempStr.substring(4, tempStr.indexOf(";"));
                } else if(browserType.indexOf("firefox") != -1) {
                    this.browser = "firefox";
                } else if(browserType.indexOf("opera") != -1) {
                    this.browser = "opera";
                } else if(browserType.indexOf("safari") != -1) {
                    this.browser = "safari";
                }
            }

        }

        public boolean isIE() {
            return this.browser != null && this.browser.equals("ie");
        }

        public boolean isFirefox() {
            return this.browser != null && this.browser.equals("firefox");
        }

        public String getVersion() {
            return this.version;
        }

        public String createPostfix(String filename) {
            if(this.isIE()) {
                return filename;
            } else {
                int dotIndex = filename.lastIndexOf(".");
                boolean dot = dotIndex != -1;
                String s1 = dot?filename.substring(0, dotIndex):filename;
                String s2 = dot?filename.substring(dotIndex):"";
                return this.isFirefox()?s1 + "_ff" + s2:null;
            }
        }
    }
}
