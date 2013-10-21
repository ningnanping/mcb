package com.xingzhe.framework.util;

import java.util.UUID;

/**
 * 类UuidUtil.java的实现描述：生成UUID序列
 * 
 * @author guoyanyan@360buy.com 2012-12-27 下午7:32:58
 */
public class UuidUtil {

    private static String separater = "-";

    public static String getUUid() {
        return UUID.randomUUID().toString().replace(separater, "");
    }

}
