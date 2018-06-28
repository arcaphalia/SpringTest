package cn.bj.zy.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 *
 * @Title: CommonUtil.java
 * @Package package com.bbrightway.util
 * @Description: TODO(转换函数和方法)
 * @version 1.0
 * @date Mar 15, 2011 
 * @author  Jiact
 */
@SuppressWarnings(value={"rawtypes", "unused","unchecked"})
public class CommonUtil {
    private final static DecimalFormat nf = new DecimalFormat("#####0.00");
    private final static DecimalFormat decimalFormat = new DecimalFormat("0.000");

    private final static String NULL_STRING = "";

    private final static Log log = LogFactory.getLog(CommonUtil.class);
   
	private static Class[] ac = { Long.class, Long.TYPE, Boolean.class,
            Boolean.TYPE, Integer.class, Integer.TYPE, Byte.class, Byte.TYPE,
            String.class, Date.class, java.sql.Date.class, Timestamp.class,
            BigDecimal.class, Float.class, Float.TYPE };
   
	private static HashSet propertyTypesForCopy = new HashSet(Arrays.asList(ac));

    /**
     *
     * @param str
     * @return
     */
    public static Double mathRound(Double str) {
        try {
            String num = decimalFormat.format(str);
            Double d = Double.valueOf(num);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0d;
    }
    /**
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        if (format.indexOf("h") > 0) {
            format = format.replace('h', 'H');
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     *
     * 功能：此功能将把指定的时间字符串，转化为给定的时间字符串
     *
     * @param sourceDate
     * @param source
     * @param target
     * @return
     * @throws Exception
     * @author zhoujun
     */
    public static String formatDate(String sourceDate, String source,
                                    String target) throws Exception {
        if (sourceDate == null || "".equals(sourceDate)) {
            return "";
        }
        SimpleDateFormat sourceDateFormat = new SimpleDateFormat(source);
        Date sourcedate = sourceDateFormat.parse(sourceDate);
        SimpleDateFormat targetDateFormat = new SimpleDateFormat(target);
        return targetDateFormat.format(sourcedate);
    }

    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    public static String formatTime(Date date) {
        return formatDate(date, "yyyy-MM-dd hh:mm");
    }

    /**
     *
     * @param str
     * @param format
     * @return
     */
    public static Date parseDate(String str, String format) {
        try {
            if (str == null || str.equals("")) {
                return null;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.parse(str);
        } catch (Exception e) {

        }
        return new Date();
    }

    /**
     *
     * @param str
     * @return
     */
    public static Date parseDate(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        if (str.length() == 6) { // Format example: 2004-08
            return parseDate(str, "yyyy-MM");
        } else if (str.length() == 8) { // Format example: 2004-08-26
            return parseDate(str, "yyyyMMdd");
        } else if (str.length() == 10) { // Format example: 2004-08-26
            return parseDate(str, "yyyy-MM-dd");
        } else if (str.length() == 13) { // Format example 2004-08-26 12
            return parseDate(str, "yyyy-MM-dd HH");
        } else if (str.length() == 16) { // Format example 2004-08-26 12:09
            return parseDate(str, "yyyy-MM-dd HH:mm");
        } else if (str.length() == 19) { // Format example 2004-08-26
            // 12:09:08
            return parseDate(str, "yyyy-MM-dd HH:mm:ss");
        } else if (str.length() >= 21) { // Format example 2004-08-26
            // 12:09:08.9
            return parseDate(str, "yyyy-MM-dd HH:mm:ss.S");
        }
        return null;
    }

    public static Double parseDouble(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }

        return Double.valueOf(str);
    }

    public static Long parseLong(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }

        return Long.valueOf(str);
    }

    public static String killNull(Object o, String ds) {
    	if (o instanceof Double)
            return killNull((Double) o, ds);
        else
            return o != null ? o.toString() : ds;
    }

    public static String killNull(Object o) {
        return killNull(o, NULL_STRING);
    }

    public static String killNull(Double o, String ds) {
        if (o != null) {
            return nf.format(o);
        } else
            return ds;
    }

    public static String killNull(Double o) {
        return killNull(o, NULL_STRING);
    }

    public static String killNull(double o) {
        if (o != 0) {
            return nf.format(o);
        } else
            return NULL_STRING;
    }

    public static String killNull(BigDecimal o) {
        return killNull(o, NULL_STRING);
    }

    public static String killNull(BigDecimal o, String nullString) {
        if (o != null) {
            return nf.format(o.doubleValue());
        }
        if (NULL_STRING.equals(nullString)) {
            return "";
        } else
            return "0";
    }

    public static String killNull(long o) {
        return String.valueOf(o);
    }

    public static String killNull(Long o) {
        return o == null ? NULL_STRING : String.valueOf(o);
    }




    public static String killNull(Date inObj) {
        if (inObj == null) {
            return NULL_STRING;
        }

        return formatDate(inObj);
    }

    public static String killEmptyString(String str) {
        if ("".equals(str)) {
            return null;
        }

        return str;
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        try {
            if (isEmpty(str)) {
                return false;
            }
            Double ret = new Double(str);
            log.debug(ret);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 
     * 字符串转BigDecimal
     *	
     * @param str
     * @return   
     * @exception    
     * @since  1.0.0
     */
    public static BigDecimal parseDecimal(String str,int scale){
		try {
			// 判断是否为空
			if(isNull(str)){
				return null;
			}
			// 精度参数大于等于0
			if(scale>=0){
				StringBuffer sb = new StringBuffer("0.");
				for(int i=0;i<scale;i++){
					sb.append("0");
				}
				DecimalFormat df = new DecimalFormat(sb.toString());
				Double re = new Double(str);
				String num = df.format(re);
				Double d = new Double(num);
				BigDecimal b = BigDecimal.valueOf(d);
				return b;
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
    }

    /**
     *
     * @param s
     * @param len
     * @return
     */
    public static String toStr(String s, int len) {
        if (s == null) {
            s = "";
        } else {
            s = s.trim();
        }
        return StringUtils.repeat("0", len - s.length()) + s;
    }

    public static String toStr(long n, int len) {
        String s = Long.toString(n);
        return StringUtils.repeat("0", len - s.length()) + s;
    }

    /**
     *
     * @param columnName
     * @return
     */
    static public String fieldName2varName(String columnName) {
        return fieldName2varName(columnName, "_");
    }

    /**
     *
     * @param columnName
     * @param regex
     * @return
     */
    static public String fieldName2varName(String columnName, String regex) {
        String controlName = "";
        String temp = null;
        if (columnName.indexOf(regex) < 0) {
            return columnName;
        }
        StringTokenizer st = new StringTokenizer(columnName, regex);
        if (st.hasMoreTokens()) {
            controlName = st.nextToken().toLowerCase();
        }
        while (st.hasMoreTokens()) {
            temp = st.nextToken().toLowerCase();
            controlName += temp.substring(0, 1).toUpperCase()
                    + temp.substring(1, temp.length());
        }
        return controlName;
    }

    static public String varName2fieldName(String varName) {
        return varName2fieldName(varName, "_");
    }

    /**
     *
     * @param varName
     * @param regex
     * @return
     */
    static public String varName2fieldName(String varName, String regex) {
        char[] temp = varName.toCharArray();
        String filedName = "";
        String[] index = new String[5];
        for (int i = 0, j = 0; i < temp.length; i++) {
            if (Character.isUpperCase(temp[i])) {
                index[j] = String.valueOf(temp[i]);
                j++;
            }
        }
        String[] strArray = varName.split("[A-Z]");
        for (int i = 0; i < strArray.length; i++) {
            if (i != strArray.length - 1) {
                filedName += strArray[i] + regex + index[i].toLowerCase();
            } else {
                filedName += strArray[i];
            }
        }
        return filedName;
    }

    public static String[] stringToStrArray(String input, String delimiter) {
        if (isEmpty(input))
            return null;
        String[] cmd = input.split(delimiter);
        return cmd;
    }

    public static List stringToStrList(String input, String delimiter) {
        if (isEmpty(input)) {
            return null;
        }
        return new ArrayList(Arrays.asList(stringToStrArray(input, delimiter)));
    }

    public static String strArrayToString(String[] needtoTrans, String delimiter) {
        return list2String(Arrays.asList(needtoTrans), delimiter);
    }

    /**
     *
     * @param list
     * @param seperator
     * @return
     */
    public static String list2String(List list, String seperator) {
        if (list == null || list.size() == 0) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    sb.append(seperator);
                }
                sb.append(list.get(i));
            }
            return sb.toString();
        }
    }

    /**
     * �ж��Ƿ�Ϊ��
     *
     * @param emptyObect
     * @return
     */
    public static boolean isEmpty(Object emptyObect) {
        boolean result = true;
        if (emptyObect == null) {
            return true;
        }
        if (emptyObect instanceof String) {
            result = emptyObect.toString().trim().length() == 0
                    || emptyObect.toString().trim().equals("null");
        } else if (emptyObect instanceof Collection) {
            result = ((Collection) emptyObect).size() == 0;
        } else {
            result = (emptyObect == null || emptyObect.toString().trim()
                    .length() < 1) ? true : false;
        }
        return result;
    }

    public static boolean isNotEmpty(Object emptyObect) {
        return !isEmpty(emptyObect);
    }



    public static StringBuffer setVar(StringBuffer temp, String name, long value) {
        return setVar(temp, name, String.valueOf(value));
    }

    public static StringBuffer setVar(StringBuffer temp, String name,
                                      String value) {
        return setVar(temp, name, (Object) value);
    }

    public static StringBuffer setVar(StringBuffer temp, String name,
                                      Object value) {
        if (temp == null || name == null) {
            return temp;
        }
        if (value == null || value.toString().length() == 0) {
            value = "";
        }
        int start = temp.indexOf(name);
        if (start < 0) {
            return temp;
        }
        return temp.replace(start, start + name.length(), value.toString());
    }

    public static Long[] stringToLong(String[] args) {
        if (args == null) {
            return null;
        }
        Long longs[] = new Long[args.length];
        for (int i = 0, n = args.length; i < n; i++) {
            longs[i] = Long.valueOf(args[i]);
        }
        return longs;
    }

    public static long[] stringTolong(String[] args) {
        if (args == null) {
            return null;
        }
        long longs[] = new long[args.length];
        for (int i = 0, n = args.length; i < n; i++) {
            longs[i] = Long.valueOf(args[i]).longValue();
        }
        return longs;
    }

    public static double[] stringTodouble(String[] args) {
        if (args == null) {
            return null;
        }
        double doubles[] = new double[args.length];
        for (int i = 0, n = args.length; i < n; i++) {
            doubles[i] = Double.valueOf(args[i]).doubleValue();
        }
        return doubles;
    }

    public static String getProperty(Object obj, String name) {
        try {
            return BeanUtils.getProperty(obj, name);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *
     * @Title: getQuarterDate
     * @Description: (计算当前季度（包含向前或向后i个季度）的起始日朄1�7)
     * i大于0是向前，i小于0是向各1�7
     * @param
     * @return
     * @return 返回类型
     * @throws
     */
    public static Date[] getQuarterDate(int i) {
        Date[] date = getQuarterDate();
        Calendar cal = Calendar.getInstance();
        if (i >= 0) {
            cal.setTime(date[0]);
            cal.add(Calendar.MONTH, -i * 3);
            date[0] = cal.getTime();
        } else {
            cal.setTime(date[1]);
            cal.add(Calendar.MONTH, -i * 3);
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
                    .getActualMaximum(Calendar.DATE));
            date[1] = cal.getTime();
        }
        return date;
    }

    /**
     *
     * @Title: getQuarterDate
     * @Description: (计算当前日期扄1�7属季度的起始日期)
     * @param
     * @param
     * @param
     * @return
     * @return 返回类型
     * @throws
     */
    public static Date[] getQuarterDate() {
        Date[] date = new Date[2];
        Date cuttent_date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(cuttent_date);
        if ((cal.get(Calendar.MONTH) + 1) % 3 == 0) {// 季度结束朄1�7
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
                    .getActualMaximum(Calendar.DATE));
            date[1] = cal.getTime();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 2, cal
                    .getActualMinimum(Calendar.DATE));
            date[0] = cal.getTime();
        } else if ((cal.get(Calendar.MONTH) + 2) % 3 == 0) {// 季度中间朄1�7
            cal.add(Calendar.MONTH, 1);
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
                    .getActualMaximum(Calendar.DATE));
            date[1] = cal.getTime();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 2, cal
                    .getActualMinimum(Calendar.DATE));
            date[0] = cal.getTime();
        } else if ((cal.get(Calendar.MONTH) + 3) % 3 == 0) {// 季度起始朄1�7
            cal.add(Calendar.MONTH, 2);
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
                    .getActualMaximum(Calendar.DATE));
            date[1] = cal.getTime();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 2, cal
                    .getActualMinimum(Calendar.DATE));
            date[0] = cal.getTime();
        }
        return date;
    }

    /**
     *
     * @return
     */
    public static String getNowDate() {
        Calendar now = Calendar.getInstance();
        return getDateStr(now);
    }
    
    /**
     * 获取上个月日期 
     * @return
     */
    public static String getLastMonth(){
    	Calendar now = Calendar.getInstance();
    	now.add(Calendar.MONTH, -1);
    	return getDateStr(now);
    }
    /**
     * ȡ��"YYYY-MM-DD HH:MI:SS"
     */
    public static String getNowFullDate() {
        Calendar now = Calendar.getInstance();
        return getDateStr(now) + " " + getHour(now) + ":" + getMinute(now)
                + ":" + getSecond(now);
    }

    public static String getDateStr(Calendar cal) {
        return getYear(cal) + "-" + getMonth(cal) + "-" + getDay(cal);
    }

    public static String getYear(Calendar cal) {
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    public static String getMonth(Calendar cal) {
        return strLen(String.valueOf(cal.get(Calendar.MONTH) + 1), 2);
    }

    public static String getDay(Calendar cal) {
        return strLen(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), 2);
    }

    public static String getHour(Calendar cal) {
        return strLen(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2);
    }

    public static String getMinute(Calendar cal) {
        return strLen(String.valueOf(cal.get(Calendar.MINUTE)), 2);
    }

    public static String getSecond(Calendar cal) {
        return strLen(String.valueOf(cal.get(Calendar.SECOND)), 2);
    }

    public static String strLen(String s, int len) {
        if (s == null) {
            s = "";
        } else
            s = s.trim();
        int strLen = s.length();
        for (int i = 0; i < len - strLen; i++) {
            s = "0" + s;
        }
        return s;
    }

    public static Timestamp convertStrToTimestamp(String timeStampStr) {
        Timestamp returnT = null;
        if (timeStampStr != null && !timeStampStr.trim().equals("")) {
            returnT = new Timestamp(parseDate(timeStampStr).getTime());
        }
        return returnT;
    }

    public static String getModifiedValue(Object newValue, Object oldValue,
                                          boolean isNull) {
        String str1 = newValue == null ? " " : CommonUtil.killNull(newValue);
        String str2 = oldValue == null ? " " : CommonUtil.killNull(oldValue);
        if (isNull)
            return str1;
        if (!str1.equals(str2)) {
            return str1 + "&nbsp;<font color='red'>[" + str2 + "]</font>";
        }
        return str1;
    }

    public static Throwable getRootCause(Throwable exception) {
        if (exception.getCause() != null) {
            return getRootCause(exception.getCause());
        } else {
            return exception;
        }
    }

    public static String killZero(long id) {
        if (id == 0) {
            return "";
        } else {
            return String.valueOf(id);
        }
    }

    /**
     * SBC case -> DBC 全角转换为半角���
     *
     * @param SBCstr
     * @return
     */
    public static final String SBC_to_DBC(String SBCstr) {
        if (CommonUtil.isEmpty(SBCstr)) {
            return "";
        } else {
            SBCstr = SBCstr.trim();
        }
        StringBuffer outStr = new StringBuffer();
        String str = "";
        byte[] b = null;
        StringBuffer sb = new StringBuffer(SBCstr);
        for (int i = 0; i < SBCstr.length(); i++) {
            try {
                str = sb.substring(i, i + 1);
                b = str.getBytes("unicode");
            } catch (java.io.UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (b[3] == -1) {
                b[2] = (byte) (b[2] + 32);
                b[3] = 0;
                try {
                    outStr.append(new String(b, "unicode"));
                } catch (java.io.UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                outStr.append(str);
            }
        }
        return outStr.toString();
    }

    /**
     * 半角转全规1�7
     *
     * @param input
     *            String.
     * @return 全角字符丄1�7.
     */
    public static String ToSBC(String input) {
        if ("".equals(input)) {
            return "";
        } else {
            char c[] = input.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == ' ') {
                    c[i] = '\u3000'; // 采用十六进制,相当于十进制的1�7288

                } else if (c[i] < '\177') { // 采用八进刄1�7,相当于十进制的1�77
                    c[i] = (char) (c[i] + 65248);

                }
            }
            return new String(c);
        }

    }

    /**
     * 全角转半规1�7
     *
     * @param input
     *            String.
     * @return 半角字符丄1�7
     */
    public static String ToDBC(String input) {

        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);

        return returnString;
    }

    public static StringBuffer addValues(Object obj, StringBuffer sb) {

        sb = addValues(obj, sb, "content");

        return sb;
    }

    public static StringBuffer addValues(Object obj, StringBuffer sb,
                                         String index) {
        if (index.equals("start")) {
            sb.append("[");
            sb.append("\"");
        } else {
            sb.append("\"");
        }
        if (!isEmpty(obj)) {
            sb.append(killNull(obj));
        }
        if (index.equals("end")) {
            sb.append("\"");
            sb.append("]");
        } else {
            sb.append("\"");
            sb.append(",");
        }
        return sb;
    }

    public static String decimalFormatDouble(double d) {
        String doubleToStr = "";
        DecimalFormat decfmt = new DecimalFormat("##0.00");
        doubleToStr = decfmt.format(d);
        return doubleToStr;
    }

    public static String decimalFormat(Double d) {
        if (d == null) {
            return "0.00";
        }
        String doubleToStr = "";
        DecimalFormat decfmt = new DecimalFormat("##0.00");
        doubleToStr = decfmt.format(d);
        return doubleToStr;
    }

    /**
     *
     * 功能：如果传过来的�1�7�为null戄1�7"null"字符串，则转换为""
     *
     * @param value
     * @return String
     */
    public static String initNullValue(String value) {
        if (value == null || value.equals("null")|| value.equals("")) {
            return "";
        } else {
            return value.trim();
        }
    }

    /**
     *
     * 功能：如果传过来的�1�7�为null戄1�7"null"字符串，则转换为"0"
     *
     * @param value
     * @return String
     */
    public static String initNullValueToZero(String value) {
        if (value == null || value.equals("null") || value.trim().equals("")) {
            return "0";
        } else {
            return value;
        }
    }

    /**
     *
     * 功能：如果传过来的�1�7�为null戄1�7"null"Double，则转换丄1�7"0.0"
     *
     * @param value
     * @return String
     */
    public static Double initNull(Double value) {
        if (value == null || value.equals("null") || value.equals("")) {
            return 0.0;
        } else {
            return value;
        }
    }

    /**
     *
     * 功能＄1�7
     * @param value
     * @return
     */
    public static String initNullObject(Object value) {
        if (value == null || value.equals("null") || value.equals("")) {
            return "";
        } else {
            return value.toString();
        }
    }


    /**
     *
     * 功能：得到传入日期的年份
     *
     * @param date
     * @return String
     */
    public static String formatDateYear(Date date) {
        String strYear = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            strYear = sdf.format(date);
        }
        return strYear;
    }

    /**
     * 功能：货币格式转换为字符类型
     *
     * @param d
     * @return String liuqingsong
     */
    public static String moneyStr(Double d) {
        DecimalFormat myformat = new DecimalFormat(
                "###,###,###,##0.00");
        String str = "";
        if (d == null || (d.toString()).equals("0.0")) {
            str = "0.00";
        } else {
            str = myformat.format(d);
        }
        return str;
    }

    public static String moneyToNumber(Object money) {
        String[] arr = String.valueOf(money).split(",");
        String arrStr = "";
        for (int i = 0; i < arr.length; i++) {
            arrStr += arr[i];
        }
        return arrStr;
    }

    /**
     * 功能：货币格式转换为字符类型
     *
     * @param d
     * @return String liuqingsong
     */
    public static String moneyStr(Object d) {
        DecimalFormat myformat = (DecimalFormat) DecimalFormat.getInstance(Locale.CHINA);
        //DecimalFormat myformat = new java.text.DecimalFormat(
        //		"###,###,###,###.00");
        myformat.applyPattern("###,###,###,###.00");
        //myformat.setGroupingSize(3);
        String str = "";
        if (d == null || (d.toString()).equals("0.0")
                || (d.toString()).equals("0")) {
            str = "0.00";
        } else {
            str = myformat.format(d);
        }
        return str;
    }

    public static String getFuturesMonth(Date sysdate) {
        Date date = new Date();
        if (sysdate != null) {
            date = sysdate;
        }

        String currentDate = formatDate(date, "yyyyMMdd");
        String currentMonth = formatDate(date, "yyyyMM");
        String startDate = currentMonth + "10";
        String endDate = currentMonth + "20";
        Long startNumber = new Long(startDate);
        Long endNumber = new Long(endDate);
        Long currentNumber = new Long(currentDate);
        if (currentNumber.longValue() > startNumber.longValue()
                && currentNumber.longValue() <= endNumber.longValue()) {
            return "";
        } else {
            if (currentNumber.longValue() > endNumber.longValue()) {
                return currentMonth;
            } else {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                calendar.add(Calendar.MONTH, -1);
                date = calendar.getTime();
                return formatDate(date, "yyyyMM");
            }
        }
    }

    public static Date getMonthFirstDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date getMonthLastDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 对象类型转换成字符类型Object->String
     *
     * @param obj
     * @return
     */
    public static String ObjToString(Object obj) {
        if (isEmpty(obj)) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     *
     * 功能：计箄1�7�日期之间的天数,大日朄1�7-小日期，结果可能为负
     *
     * @param dtFrom
     * @param dtEnd
     * @return
     */
    public static String getDaysBetweenTwoDates(Date dtFrom, Date dtEnd) {
        if (dtFrom == null || dtEnd == null) {
            return "0";
        }
        long begin = dtFrom.getTime();
        long end = dtEnd.getTime();
        long inter = end - begin;
        // if (inter < 0) {
        // inter = inter * (-1);
        // }
        long dateMillSec = 24 * 60 * 60 * 1000;
        long dateCnt = inter / dateMillSec;
        long remainder = inter % dateMillSec;
        if (remainder != 0) {
            dateCnt++;
        }
        return String.valueOf(dateCnt + 1);
    }

    /**
     * @Title: getNextDay
     * @Description: 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     * @param @param date
     * @param @param warnday
     * @param @return    设定文件
     * @return Date    返回类型
     * @throws
     */
    public static Date getNextDay(Date nowdate, int delay) {
        try {
            Date mdate = null;
            long myTime = (nowdate.getTime() / 1000) + delay * 24 * 60 * 60;
            mdate = new Date(myTime * 1000);
            return mdate;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @Title: getDaysOfDate
     * @Description: 比较两个时间的差值
     * @param @param date
     * @param @param outofdate
     * @param @return    设定文件
     * @return Long    返回类型
     * @throws
     */
    public static long getDaysOfDate(Date d1, Date d2) {
        double c = (d1.getTime() - d2.getTime()) / 1000 / 3600 / 24;
        return (long) Math.floor(c);
    }



    /**********************************                  封装类型为空时转为对象值为 0                                     *******************/
    public static String isNullZero(String parm) {
        if (parm == null || "".equals(parm)) {
            return "0";
        } else {
            return parm;
        }
    }

    public static Long isNullZero(Long parm) {
        if (parm == null) {
            return new Long(0);
        } else {
            return parm;
        }
    }

    public static Integer isNullZero(Integer parm) {
        if (parm == null) {
            return new Integer(0);
        } else {
            return parm;
        }
    }

    public static Double isNullZero(Double parm) {
        if (parm == null) {
            return new Double(0);
        } else {
            return parm;
        }
    }
    /**********************************                  封装类型为空时转为对象值为   0                                  *******************/


    public static Double strToDouble(String str) {
        if (null == str || "".equals(str)) {
            return new Double(0);
        } else {
            return new Double(str);
        }
    }

    /** 判断对象的值是否为空 */
    public static boolean isNotNull(Object obj) {
        boolean flag = true;
        if (null == obj || "".equals(obj) || "null".equalsIgnoreCase(obj.toString()))
            flag = false;
        return flag;

    }

    public static boolean isNotNull(List l) {
        if (null != l && l.size() > 0)
            return true;
        return false;
    }

    public static boolean isNotNull(String str) {
        if (null != str && str.trim().length() > 0 && !"null".equals(str))
            return true;
        return false;
    }

    public static boolean isNotNull(String[] str) {
        if (null != str && str.length > 0)
            return true;
        return false;
    }

    /** 判断对象的值是否为空 */
    public static boolean isNull(Object obj) {
        boolean flag = false;
        if (null == obj || "".equals(obj)||"null".equalsIgnoreCase(obj.toString()))
            flag = true;
        return flag;

    }
    /**
     *
     * 方法名称:isNull
     * 方法描述:判断是字符串是否为空
     * @param str
     *            字符串 参数:
     * @return boolean
     * @author yaozy
     * @date Sep 2, 2009
     */
    public static boolean isNull(String str) {
        if (null == str || "".equals(str.trim()))
            return true;
        return false;
    }

    /**
     *
     * 方法名称:isNull
     * 方法描述: 判断结果集是否为空
     * @param list
     *            结果集对象 参数:
     * @return boolean
     */
    public static boolean isNull(List<Object> list) {
        if (null == list || list.size() == 0)
            return true;
        return false;
    }
    /**
     * 方法名称:paramsRequiredVal
     * 方法描述: 参数必填(不能为空)校验
     * @param paras
     * @return  参数是否合格 true：合格/反之
     */
	public static boolean paramsRequiredVal(Object ... paras){
    	boolean boo = true;//校验合格
    	if(paras !=null && paras.length>0){
    		for(Object obj : paras){
    			if(obj == null && obj ==""){
    				boo = false;
    				break;
    			}
    		}
    	}
    	return boo;
    }

    /**
     *
     * 方法名称:isNull
     * 方法描述: 判断字符串数组是否为空
     * @param str
     *            String[] 字符串数组 参数:
     * @return boolean
     * @author yaozy
     * @date Sep 2, 2009
     */
    public static boolean isNull(String[] str) {
        if (null == str || str.length == 0)
            return true;
        return false;
    }
    /**
     * 
     * convertBean2Bean(复制bean) 
     * @param target	目标bean
     * @param source	源bean   source --> target
     * @return
     */
    public static Object convertBean2Bean(Object target,Object source) {  
		  
        if(source == null){  
            return null;  
        }          
        try {  
        	Map<String,PropertyDescriptor> propertyMap = new HashMap<String, PropertyDescriptor>();
            BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());  
            BeanInfo beanInfo_target = Introspector.getBeanInfo(target.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            PropertyDescriptor[] propertyDescriptors_target = beanInfo_target.getPropertyDescriptors(); 
            for (PropertyDescriptor property_target : propertyDescriptors_target) { 
            	propertyMap.put(property_target.getName().toLowerCase(), property_target);
            }
            for (PropertyDescriptor property : propertyDescriptors) {  
        		String key = property.getName();  
        		// 过滤class属性  
        		if (!key.equals("class") && propertyMap.containsKey(key.toLowerCase())) {  
        			PropertyDescriptor property_target =  propertyMap.get(key.toLowerCase());
        			// 得到property对应的getter方法  
        			Method getter = property.getReadMethod();  
        			Object value = getter.invoke(source);  
        			Method setter = property_target.getWriteMethod();
        			if (null != value && !"".equals(value) && setter != null) {  
                        String fieldType = property_target.getPropertyType().getSimpleName();  
                        if ("String".equals(fieldType)) {  
                            setter.invoke(target, value);  
                        } else if ("Date".equals(fieldType)) {
                        	Date temp = null;
                        	if(value instanceof String){
                        		temp = DateUtil.getDateWithStdFormat((String)value);
                        	}else{
                        		temp = (Date)value; 
                        	}
                            setter.invoke(target, temp);  
                        } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {  
                        	Integer intval = null;
                        	if(value instanceof String){
                        		intval = Integer.parseInt((String)value);  
                        	}else{
                        		intval = (Integer)value; 
                        	}
                            setter.invoke(target, intval);  
                        } else if ("Long".equalsIgnoreCase(fieldType)) {  
                        	Long temp = null;
                        	if(value instanceof String){
                        		temp = Long.parseLong((String)value);  
                        	}else{
                        		temp = (Long)value;  
                        	}
                            setter.invoke(target, temp);  
                        } else if ("BigDecimal".equalsIgnoreCase(fieldType)) {  
                        	BigDecimal temp = null;
                        	if(value instanceof String){
                        		temp = new BigDecimal((String)value);
                        	}else{
                        		temp = (BigDecimal)value;
                        	}
                        	setter.invoke(target, temp);  
                        } else if ("Double".equalsIgnoreCase(fieldType)) {  
                        	Double temp = null;  
                        	if(value instanceof String){
                        		temp = Double.parseDouble((String)value);  
                        	}else{
                        		temp =(Double)value;  
                        	}
                            setter.invoke(target, temp);  
                        } else if ("Boolean".equalsIgnoreCase(fieldType)) {  
                        	Boolean temp = null;  
                        	if(value instanceof String){
                        		temp = Boolean.parseBoolean((String)value);  
                        	}else{
                        		temp = (Boolean)value;  
                        	}
                            setter.invoke(target, temp);  
                        } else {  
                            System.out.println("not supper type" + fieldType);  
                        }  
                    }
            	}  
            }  
        } catch (Exception e) {  
        	e.printStackTrace();
        }  
  
        return target;  
  
    } 
    
    public static <T> List<List<T>> splitList(List<T> list, int maxListSize) {
        List<List<T>> splittedList = new ArrayList<List<T>>();
        int itemsRemaining = list.size();
        int start = 0;
        while (itemsRemaining != 0) {
            int end = itemsRemaining >= maxListSize ? (start + maxListSize) : (start + itemsRemaining);
            splittedList.add(list.subList(start, end));
            int sizeOfFinalList = end - start;
            itemsRemaining = itemsRemaining - sizeOfFinalList;
            start = start + sizeOfFinalList;
        }
        return splittedList;
    }

}
