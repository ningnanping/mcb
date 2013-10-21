package com.xingzhe.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类NumberUtil.java的实现描述：数字相关工具类
 * 
 * @author yfliulin@360buy.com 2012-6-13 下午05:41:53
 */
public class NumberUtil {

    /**
     * 单位 : K
     */
    public static final char UNIT_K = 'K';

    /**
     * 单位 : M
     */
    public static final char UNIT_M = 'M';

    /**
     * 单位 : G
     */
    public static final char UNIT_G = 'G';

    /**
     * 要转换的单位常量
     */
    private static char[]    units  = { 'B', 'K', 'M', 'G' };

    /**
     * 根据字节转换成相应的单位。 { 'B', 'K', 'M', 'G' }
     * 
     * @param value 要转换的值
     * @param unit 单位标识#{units}
     * @return
     */
    public static Double convertByteToUnits(Long value, char unit) {

        // 验证单位是否合法
        if (Arrays.binarySearch(units, unit) == -1) {
            return null;
        }
        double realValue = value;
        switch (unit) {
            case 'K': {
                realValue = (value / 1024D);
                break;
            }
            case 'M': {
                realValue = ((value / (1024 * 1024.0) * 100) / 100);
                break;
            }
            case 'G': {
                realValue = ((value / (1024 * 1024 * 1024.0) * 100) / 100);
                break;
            }
        }
        // new DecimalFormat("#.00").format(realValue)
        // System.out.println("realValue:" + realValue + "format:" + new DecimalFormat("#.00").format(realValue));
        // System.out.println("dddddddddd" + d);
        // return new BigDecimal(realValue).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
        // System.out.println(new BigDecimal(realValue).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue());
        return Double.parseDouble(new DecimalFormat("#0.00").format(realValue));
    }

    /**
     * 除法运算
     * @param double1 被除数
     * @param double2 除数
     * @param scale 精确小数点
     * @return double
     */
    public static double div(double double1, double double2, int scale) {
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(double1));
        BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(double2));
        return bigDecimal1.divide(bigDecimal2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 从字符串中分离出整数
     * 
     * @param str
     * @return
     */
    public static List<Integer> splitIntegerFromString(String str) {
        List<Integer> integerList = null;
        if (null == str) {
            return integerList;
        }
        integerList = new ArrayList<Integer>();
        String numDict = "0123456789";
        char[] strChars = str.toCharArray();
        StringBuffer sbuffer = new StringBuffer();
        for (char ch : strChars) {
            if (numDict.indexOf(ch) != -1) {
                sbuffer.append(ch);
            } else {
                if (sbuffer.length() > 0) {
                    integerList.add(Integer.valueOf(sbuffer.toString()));
                    sbuffer.setLength(0);
                }
                continue;
            }
        }
        if (sbuffer.length() > 0) {
            integerList.add(Integer.valueOf(sbuffer.toString()));
            sbuffer.setLength(0);
        }

        return integerList;
    }
}
