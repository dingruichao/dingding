//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.ext.convert;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

public class CustomMappingJacksonHttpMessageConverter extends MappingJacksonHttpMessageConverter {
    public CustomMappingJacksonHttpMessageConverter() {
    }

    public void setFailOnUnknownProperties(boolean state) {
        ObjectMapper objectMapper = this.getObjectMapper();
        objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, state);
    }

    public void setSerializationInclusion(Inclusion inclusion) {
        ObjectMapper objectMapper = this.getObjectMapper();
        objectMapper.getSerializationConfig().setSerializationInclusion(inclusion);
    }
}
