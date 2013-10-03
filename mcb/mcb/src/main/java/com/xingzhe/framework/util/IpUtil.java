package com.xingzhe.framework.util;

/**
 * Created by IntelliJ IDEA.
 * User: zhangjian
 * Date: 2012-12-17
 * Time: 10:56:16
 * To change this template use File | Settings | File Templates.
 */
public class IpUtil {


    public static boolean isIp(String ip) {
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        if (ip.matches(regex)) {
            return true;
        }
        return false;
    }

}
