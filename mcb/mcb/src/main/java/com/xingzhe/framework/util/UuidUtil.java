/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
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
