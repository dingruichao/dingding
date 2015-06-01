//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.domain.common.base;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseBean implements Serializable {
    private static final long serialVersionUID = -1231524027443366825L;

    public BaseBean() {
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
