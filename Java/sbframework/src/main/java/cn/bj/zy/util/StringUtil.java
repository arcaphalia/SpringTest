package cn.bj.zy.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 *  join: 将字符串数组或者字符串列表连接成字符串
	 *  @param list
	 *  @param sep
	 *  @return
	 *  @author huang yukun
	 */
	public static String join(List<String> list, String sep){
		if(CommonUtil.isEmpty(list))
			return "";
		if(CommonUtil.isEmpty(sep))
			sep = ",";
		StringBuffer sb = new StringBuffer();
		for (String s : list) {
			sb.append(s).append(sep);
		}
		return sb.substring(0, sb.length() - sep.length());
	}
	
	/**
	 *  join: 将字符串数组或者字符串列表连接成字符串
	 *  @param list
	 *  @return
	 *  @author huang yukun
	 */
	public static String join(List<String> list){
		return join(list, ",");
	}
	
	/**
	 *  join: 将字符串数组或者字符串列表连接成字符串
	 *  @param list
	 *  @param sep
	 *  @return
	 *  @author huang yukun
	 */
	public static String join(String[] list, String sep){
		if(CommonUtil.isNull(list))
			return "";
		if(CommonUtil.isNull(sep))
			sep = ",";
		StringBuffer sb = new StringBuffer();
		for (String s : list) {
			sb.append(s).append(sep);
		}
		return sb.substring(0, sb.length() - sep.length());
	}
	
	/**
	 *  join: 将字符串数组或者字符串列表连接成字符串
	 *  @param list
	 *  @return
	 *  @author huang yukun
	 */
	public static String join(String[] list){
		return join(list, ",");
	}

	/**
     * 匹配是否包含数字
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return
     * @author yutao
     * @date 2016年11月14日下午7:41:22
     */
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
	
	/**
	 *  NumberFormat:格式化Double类型的小数
	 *  @param val
	 *  @param DecimalPlace 小数点位数
	 *  @return
	 *  @author huang yukun
	 */
	public static String NumberFormat(Double val, Integer DecimalPlace){
		StringBuffer sb = new StringBuffer();
		sb.append("#.");
		for(int i=0; i<DecimalPlace; ++i)
			sb.append("0");
		java.text.DecimalFormat df=new java.text.DecimalFormat(sb.toString()); 
		return df.format(val);
	}
	
	/**
	 *  NumberFormat: 格式化Double类型的小数
	 *  @param val
	 *  @param DecimalPlace
	 *  @return
	 *  @author huang yukun
	 */
	public static String NumberFormat(String val, Integer DecimalPlace){
		if(!isNumeric(val))
			return val;
		else{
			BigDecimal bd = new BigDecimal(val); 
			bd = bd.setScale(DecimalPlace,BigDecimal.ROUND_HALF_UP);
			return bd.toString();
		}
	}
}
