package com.ruhua.common.utils;

//import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil {

    private static final Calendar CALENDAR = Calendar.getInstance();

    /**
     * 功能描述：格式化日期
     * 
     * @param dateStr
     *             String 字符型日期
     * @param format
     *             String 格式
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format) {
        try {
            return (Date) new SimpleDateFormat(format).parse(dateStr);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能描述：格式化日期
     * 
     * @param dateStr
     *             String 字符型日期：YYYY-MM-DD 格式
     * @return Date
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy/MM/dd");
    }

    /**
     * 功能描述：格式化输出日期
     * 
     * @param date
     *             Date 日期
     * @param format
     *             String 格式
     * @return 返回字符型日期
     */
    public static String format(Date date, String format) {
        String result = "";

        if (date != null) {
            result = new SimpleDateFormat(format).format(date);
        }
        return result;
    }

    /**
     * 功能描述：
     * 
     * @param date
     *             Date 日期
     * @return
     */
    public static String format(Date date) {
        return format(date, "yyyy/MM/dd");
    }

    /**
     * 功能描述：返回年份
     * 
     * @param date
     *             Date 日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月份
     * 
     * @param date
     *             Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日份
     * 
     * @param date
     *             Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     * 
     * @param date
     *             日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分钟
     * 
     * @param date
     *             日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     * 
     * @param date
     *             Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫秒
     * 
     * @param date
     *             日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 功能描述：返回字符型日期
     * 
     * @param date
     *             日期
     * @return 返回字符型日期 yyyy/MM/dd 格式
     */
    public static String getDate(Date date) {
        return format(date, "yyyy/MM/dd");
    }

    /**
     * 功能描述：返回字符型时间
     * 
     * @param date
     *             Date 日期
     * @return 返回字符型时间 HH:mm:ss 格式
     */
    public static String getTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     * 功能描述：返回字符型日期时间
     * 
     * @param date
     *             Date 日期
     * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
     */
    public static String getDateTime(Date date) {
        return format(date, "yyyy/MM/dd HH:mm:ss");
    }

    public static String getDateForKiosk(Date date) {
        return format(date, "yyyyMMddHHmmss");
    }

    /**
     * 得到日期date加上dayNum天后的日期（与addDate效果相同，只是换了一种实现）
     * @author lhy
     * @param date
     * @param dayNum
     * @return
     */
    public static Date increaseDate(Date date, int dayNum) {
        synchronized (CALENDAR){
            CALENDAR.setTime(date);
            CALENDAR.add(Calendar.DATE, dayNum);
            date.setTime(CALENDAR.getTime().getTime());
            return date;
        }
    }

    /**
     * 功能描述：日期相加
     * 
     * @param date
     *             Date 日期
     * @param day
     *             int 天数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day) {
        long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * 功能描述：日期相减
     * 
     * @param date
     *             Date 日期
     * @param date1
     *             Date 日期
     * @return 返回相减后的日期
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 功能描述：取得指定月份的第一天
     * 
     * @param strdate
     *             String 字符型日期
     * @return String yyyy-MM-dd 格式
     */
    public static String getMonthBegin(String strdate) {
        return format(parseDate(strdate), "yyyy-MM") + "-01";
    }

    /**
     * 功能描述：取得指定月份的最后一天
     * 
     * @param strdate
     *             String 字符型日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String getMonthEnd(String strdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(getMonthBegin(strdate)));
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime());
    }

    /**
     * 功能描述：常用的格式化日期
     * 
     * @param date
     *             Date 日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String formatDate(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    /**
     * 功能描述：以指定的格式来格式化日期
     * 
     * @param date
     *             Date 日期
     * @param format
     *             String 格式
     * @return String 日期字符串
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 功能描述：由系统时间来构造格式化日期
     * 
     * @param time
     *             Date 日期
     * @param time
     *             String 格式
     * @return String 日期字符串
     */
    public static String getSystem_Date(long time) {
        Date date = new Date(time);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 功能描述：当前日期往后加多少天
     * 
     * @param date
     *             Date 日期
     * @param dateNum
     *             String 格式
     * @return String 日期字符串 yyyy-MM-dd
     */
    public static String getDateTimeAddDate(String date , int dateNum) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date datetmp = null;
        try {
            datetmp = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(datetmp); 
        calendar.add(calendar.DATE, dateNum);     
        
        return getSystem_Date(calendar.getTime(),"yyyy-MM-dd");
    }

    
    /**
     * 功能描述：由系统时间来构造格式化日期
     * 
     * @param dateStr
     *              日期 字符串
     * @return String 日期字符串
     */
    public static String getSystemDateByString(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = null;
        try{
        	date = df.format(df.parse(dateStr)); 
        }catch(Exception ex){
        	
        }
        return date; 
    }

    /**
     * 功能描述：由系统时间来构造格式化日期
     * 
     * @param date
     *             Date 日期
     * @param date
     *             String 格式
     * @return String 日期字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getSystem_Date(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 功能描述：由系统时间来构造格式化日期
     *
     * @param date
     *             Date 日期
     * @param date
     *             String 格式
     * @return String 日期字符串 yyyy-MM-dd
     */
    public static String getSystem_Date(Date date,String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    
    public static String formatDate(Date date, String style) {
        return formatDateByFormat(date, style);
    }

    public static String turnDate(String showDate, String format, int interDay) {
        // 日期加指定天数
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(format);
        Date tempDate_001 = null;
        try {
            tempDate_001 = df.parse(showDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (null == tempDate_001) {
            return null;
        }
        cal.setTime(tempDate_001);
        cal.add(Calendar.DAY_OF_MONTH, interDay);
        String next = df.format(cal.getTime());
        return next;
    }

    /**
     * 按指定格式获取几天前后的日期 默认 2013-04-09
     * @param date
     * @return
     */
    public static String getYesToday(Date date,int days,String format){
        if(null!=date){

            if(null==format||"".equals(format)){
                 return format(addDate(date,days),"yyyy-MM-dd");
            }else{
               return format(addDate(date,days),format);
            }
        }else{
            return null;
        }
    }
    public static void main(String[] args) {

        // System.out.println(getMonthBegin(formatDate(d).toString()));
        // System.out.println(getMonthBegin("2008/07/19"));
        // System.out.println(getMonthEnd("2008/07/19"));
        // System.out.println(addDate(d, 15).toString());
        // System.out.println(getDateForKiosk(new Date()).toString());
        // System.out.println(DateUtil.parseDate("2012-0", "yyyy-MM-dd"));
        // System.out.println(DateUtil.turnDate("20120217", "yyyyMMdd", -15));
//        System.out.println(DateUtil.getDatesBetween2Date("2013-01-01", "2013-01-10"));
    }

    /**
     * 获得两个日期（字符串）之间的所有日期
     * 
     * @param start_date
     *             String 开始日期 EX：“2012-01-01” 强制输入参数
     * @param end_date
     *             String 结束日期 EX：“2012-01-02” 强制输入参数
     * @return
     */
//    public static List<String> getDatesBetween2Date(String start_date, String end_date) {
//        List<String> result = new ArrayList<String>();
//        try {
//            start_date = StringUtils.trim(start_date);
//            end_date = StringUtils.trim(end_date);
//            if (StringUtils.isEmpty(start_date) || StringUtils.isEmpty(end_date)) {
//                return result;
//            }
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            int recAfter = diffDate(sdf.parse(end_date), sdf.parse(start_date));
//            if (recAfter < 0) {
//                String temp = start_date;
//                start_date = end_date;
//                end_date = temp;
//                recAfter = -1 * recAfter;
//            }
//            result.add(start_date);
//            String tempStart_date = new String(start_date.getBytes("UTF-8"), "UTF-8");
//            while (recAfter > 0) {
//                tempStart_date = DateUtil.turnDate(tempStart_date, "yyyy-MM-dd", 1);
//                result.add(tempStart_date);
//                recAfter--;
//            }
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    
    /**
     * 获得两个日期（字符串）之间的所有日期
     * 
     * @param minutes 分钟
     * @param 
     * @return
     */
    public static String minutesToDayHourMinutes(long minutes) {
      
    	String 	timeStr = "";
    	
    	long days = minutes / (60 * 24);		 
    	long daysTmp = minutes % (60 * 24);		 
    	long hour = daysTmp/ 60;
    	long min = daysTmp% 60;
    	
//    	if(days != 0){
//    		timeStr +=  days + "天";
//    	}
//    	if(hour != 0)
//    		timeStr +=  hour + "小时";
//		
//    	timeStr += min + "分钟";
    	
    	timeStr = days + "天" + hour + "小时"  + min + "分钟";
    	
        return timeStr;
    }
}