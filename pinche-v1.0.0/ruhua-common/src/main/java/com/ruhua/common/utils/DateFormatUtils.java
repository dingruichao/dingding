//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public DateFormatUtils() {
    }

    public static String format(Date date, String format) {
        if(date == null) {
            return null;
        } else {
            String resultStr = "";
            String formatTemp = format;
            if(format == null || "".equals(format)) {
                formatTemp = "yyyy-MM-dd";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(formatTemp);
            resultStr = dateFormat.format(date).trim();
            return resultStr;
        }
    }

    public static Date parse(String str, String format) throws ParseException {
        String formatTemp = format;
        if(format == null || "".equals(format)) {
            formatTemp = "yyyy-MM-dd";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(formatTemp);
        return dateFormat.parse(str);
    }

    public static Date parse(String str) throws ParseException {
        String[] parsePatterns = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyyMMdd", "yyyyMMddHHmmss", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss"};
        return parseDateWithLeniency(str, parsePatterns, false);
    }

    private static Date parseDateWithLeniency(String str, String[] parsePatterns, boolean lenient) throws ParseException {
        if(str != null && parsePatterns != null) {
            SimpleDateFormat parser = new SimpleDateFormat();
            parser.setLenient(lenient);
            ParsePosition pos = new ParsePosition(0);
            String[] arr$ = parsePatterns;
            int len$ = parsePatterns.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String parsePattern = arr$[i$];
                String pattern = parsePattern;
                if(parsePattern.endsWith("ZZ")) {
                    pattern = parsePattern.substring(0, parsePattern.length() - 1);
                }

                parser.applyPattern(pattern);
                pos.setIndex(0);
                String str2 = str;
                if(parsePattern.endsWith("ZZ")) {
                    str2 = str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2");
                }

                Date date = parser.parse(str2, pos);
                if(date != null && pos.getIndex() == str2.length()) {
                    return date;
                }
            }

            throw new ParseException("Unable to parse the date: " + str, -1);
        } else {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
    }
}
