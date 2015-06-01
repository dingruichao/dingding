//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.ext.exception.resolver;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruhua.common.springmvc.exception.resolver.AjaxRequestExceptionResolverResultStrategy;
import com.ruhua.common.springmvc.exception.resolver.DefaultAjaxRequestExceptionResolverResultStrategy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

public abstract class ResponseBodyExceptionResolver extends AbstractHandlerExceptionResolver {
    private List<HttpMessageConverter<?>> messageConverters;
    protected AjaxRequestExceptionResolverResultStrategy ajaxRequestExceptionResolverResultStrategy = new DefaultAjaxRequestExceptionResolverResultStrategy();

    public ResponseBodyExceptionResolver() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        this.messageConverters = new ArrayList();
        this.messageConverters.add(new ByteArrayHttpMessageConverter());
        this.messageConverters.add(stringHttpMessageConverter);
        this.messageConverters.add(new SourceHttpMessageConverter());
        this.messageConverters.add(new XmlAwareFormHttpMessageConverter());
    }

    public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        this.messageConverters = messageConverters;
    }

    public List<HttpMessageConverter<?>> getMessageConverters() {
        return this.messageConverters;
    }

    public void setAjaxRequestExceptionResolverResultStrategy(AjaxRequestExceptionResolverResultStrategy ajaxRequestExceptionResolverResultStrategy) {
        this.ajaxRequestExceptionResolverResultStrategy = ajaxRequestExceptionResolverResultStrategy;
    }

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if(handler != null && handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            ResponseBody responseBody = (ResponseBody)AnnotationUtils.findAnnotation(handlerMethod.getMethod(), ResponseBody.class);
            if(responseBody != null) {
                try {
                    return this.doResolve(request, response, handlerMethod, ex);
                } catch (Exception var8) {
                    this.logger.warn("Handling of @ResponseBody resulted in Exception", var8);
                }
            }

            return null;
        } else {
            return null;
        }
    }

    protected abstract ModelAndView doResolve(HttpServletRequest var1, HttpServletResponse var2, HandlerMethod var3, Exception var4) throws Exception;

    protected ModelAndView resolveByHttpMessageConverter(Object returnValue, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(request);
        List acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if(acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }

        MediaType.sortByQualityValue(acceptedMediaTypes);
        ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(response);
        Class returnValueType = returnValue.getClass();
        if(this.getMessageConverters() != null) {
            Iterator i$ = acceptedMediaTypes.iterator();

            while(i$.hasNext()) {
                MediaType acceptedMediaType = (MediaType)i$.next();
                Iterator i$1 = this.getMessageConverters().iterator();

                while(i$1.hasNext()) {
                    HttpMessageConverter messageConverter = (HttpMessageConverter)i$1.next();
                    if(messageConverter.canWrite(returnValueType, acceptedMediaType)) {
                        messageConverter.write(returnValue, acceptedMediaType, outputMessage);
                        return new ModelAndView();
                    }
                }
            }
        }

        if(this.logger.isWarnEnabled()) {
            this.logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);
        }

        return null;
    }
}
