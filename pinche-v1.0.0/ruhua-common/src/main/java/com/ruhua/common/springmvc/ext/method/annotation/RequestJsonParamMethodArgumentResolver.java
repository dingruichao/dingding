//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.ext.method.annotation;


import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ruhua.common.springmvc.ext.bind.annotation.RequestJsonParam;
import com.ruhua.common.utils.json.JsonBinder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RequestJsonParamMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final JsonBinder JSON_BINDER = JsonBinder.buildNormalBinder();

    public RequestJsonParamMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestJsonParam.class);
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestJsonParam attribute = (RequestJsonParam)parameter.getParameterAnnotation(RequestJsonParam.class);
        String name = attribute.value();
        String value = webRequest.getParameter(name);
        Class paramType = parameter.getParameterType();
        List annotations = this.getValidateAnnotations(parameter);
        Object result = null;
        WebDataBinder binder = null;
        BindingResult target;
        if(value == null) {
            if(attribute.required()) {
                binder = binderFactory.createBinder(webRequest, result, name);
                target = binder.getBindingResult();
                target.addError(new FieldError(name, name, name + " is required"));
                throw new MethodArgumentNotValidException(parameter, target);
            }
        } else if(Map.class.isAssignableFrom(paramType)) {
            Map var23 = (Map)JSON_BINDER.fromJson(value, HashMap.class);
            if(var23 != null) {
                HashMap targets = new HashMap();
                Type i = parameter.getGenericParameterType();
                Class i$ = Object.class;
                Class t = Object.class;
                if(i instanceof ParameterizedType) {
                    i$ = (Class)((ParameterizedType)i).getActualTypeArguments()[0];
                    t = (Class)((ParameterizedType)i).getActualTypeArguments()[1];
                }

                Set keys = var23.keySet();
                Object realKey = null;
                Object realValue = null;
                Object originValue = null;
                Iterator i$1 = keys.iterator();

                while(i$1.hasNext()) {
                    Object key = i$1.next();
                    if(!String.class.equals(key.getClass())) {
                        key = JSON_BINDER.toJson(key);
                    }

                    if(!Object.class.equals(i$) && !String.class.equals(i$)) {
                        realKey = JSON_BINDER.fromJson((String)key, i$);
                    } else {
                        realKey = key;
                    }

                    originValue = var23.get(key);
                    if(!String.class.equals(originValue.getClass())) {
                        originValue = JSON_BINDER.toJson(originValue);
                    }

                    if(!Object.class.equals(t) && !String.class.equals(t)) {
                        realValue = JSON_BINDER.fromJson((String)originValue, t);
                    } else {
                        realValue = originValue;
                    }

                    binder = binderFactory.createBinder(webRequest, realValue, name + "[" + key + "]");
                    realValue = binder.convertIfNecessary(realValue, t);
                    this.validateIfApplicable(binder, parameter, annotations);
                    targets.put(realKey, realValue);
                }

                result = targets;
            }
        } else {
            target = null;
            result = JSON_BINDER.fromJson(value, paramType);
            Object var24;
            int var28;
            if(paramType.isArray()) {
                if(result != null) {
                    Object[] var25 = (Object[])((Object[])result);

                    for(var28 = 0; var28 < var25.length; ++var28) {
                        var24 = var25[var28];
                        binder = binderFactory.createBinder(webRequest, var24, name + "[" + var28 + "]");
                        this.validateIfApplicable(binder, parameter, annotations);
                    }
                }
            } else if(List.class.isAssignableFrom(paramType)) {
                if(result != null) {
                    List var26 = (List)result;
                    var28 = var26.size();

                    for(int var30 = 0; var30 < var28; ++var30) {
                        var24 = var26.get(var30);
                        binder = binderFactory.createBinder(webRequest, var24, name + "[" + var30 + "]");
                        this.validateIfApplicable(binder, parameter, annotations);
                    }
                }
            } else if(Set.class.isAssignableFrom(paramType) && result != null) {
                Set var27 = (Set)result;
                byte var29 = 0;
                Iterator var31 = var27.iterator();

                while(var31.hasNext()) {
                    Object var32 = var31.next();
                    binder = binderFactory.createBinder(webRequest, var32, name + "[" + var29 + "]");
                    this.validateIfApplicable(binder, parameter, annotations);
                }
            }
        }

        if(result != null) {
            binder = binderFactory.createBinder(webRequest, result, name);
            result = binder.convertIfNecessary(result, paramType, parameter);
            this.validateIfApplicable(binder, parameter, annotations);
            mavContainer.addAttribute(name, result);
        }

        return result;
    }

    private List<Annotation> getValidateAnnotations(MethodParameter parameter) {
        Annotation[] annotations = parameter.getParameterAnnotations();
        ArrayList results = new ArrayList();
        return results;
    }

    private void validateIfApplicable(WebDataBinder binder, MethodParameter parameter, List<Annotation> annotations) throws MethodArgumentNotValidException {
        Iterator result = annotations.iterator();

        while(result.hasNext()) {
            Annotation annot = (Annotation)result.next();
            Object hints = AnnotationUtils.getValue(annot);
            binder.validate(hints instanceof Object[]?(Object[])((Object[])hints):new Object[]{hints});
        }

        BindingResult result1 = binder.getBindingResult();
        if(result1.hasErrors()) {
            throw new MethodArgumentNotValidException(parameter, result1);
        }
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSON_BINDER.setMapper(objectMapper);
    }
}
