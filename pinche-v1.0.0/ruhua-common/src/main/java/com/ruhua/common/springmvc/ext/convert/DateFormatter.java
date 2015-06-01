//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.ext.convert;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import com.ruhua.common.utils.DateFormatUtils;
import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private String format = "yyyy-MM-dd HH:mm:ss";

    public DateFormatter() {
    }

    public Date parse(String text, Locale locale) throws ParseException {
        if(text != null && text.trim().length() != 0) {
            try {
                return DateFormatUtils.parse(text);
            } catch (ParseException var4) {
                return DateFormatUtils.parse(text, this.format);
            }
        } else {
            return null;
        }
    }

    public String print(Date date, Locale locale) {
        return date == null?null: DateFormatUtils.format(date, this.format);
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
