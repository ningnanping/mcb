package com.xingzhe.framework.util;

import java.util.UUID;

/**
 * 类UuidUtil.java的实现描述：生成UUID序列
 * 
 */
public class UuidUtil {

    private static String separater = "-";

    public static String getUUid() {
        return UUID.randomUUID().toString().replace(separater, "");
    }

}
