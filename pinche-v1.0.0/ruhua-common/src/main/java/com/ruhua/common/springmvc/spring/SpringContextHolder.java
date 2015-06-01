//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.spring;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware {
    private static final Logger logger = Logger.getLogger(SpringContextHolder.class);
    private static ApplicationContext applicationContext = null;

    public SpringContextHolder() {
    }

    private static void initApplicationContext(ApplicationContext _applicationContext) {
        logger.debug("注入ApplicationContext到SpringContextHolder:" + applicationContext);
        if(applicationContext != null) {
            logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + applicationContext);
        }

        applicationContext = _applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        initApplicationContext(applicationContext);
    }

    public void destroy() throws Exception {
        clear();
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        assertContext();
        return (T)applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        assertContext();
        return applicationContext.getBean(name, requiredType);
    }

    public static void clear() {
        logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    private static void assertContext() {
        getApplicationContext();
        if(applicationContext == null) {
            throw new IllegalStateException("applicaitonContext为空.");
        }
    }
}
