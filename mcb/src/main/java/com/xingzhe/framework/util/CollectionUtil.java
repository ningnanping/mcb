/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package com.xingzhe.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * 类CollectionUtil.java的实现描述：集合工具类
 * 
 * @author liulin 2012-11-29 下午10:43:15
 */
public class CollectionUtil {

    /**
     * 些方法，会先移除集合中 null 的元素，在做比较.
     * 
     * @param col
     */
    public static boolean isEmpty(Collection<Object> col) {
        removeNull(col);
        return CollectionUtils.isEmpty(col);
    }

    /**
     * 移除集合中 null 元素.
     * 
     * @param list
     */
    public static void removeNull(Collection<Object> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        Iterator<Object> iter = list.iterator();
        while (iter.hasNext()) {
            if (iter.next() == null) {
                iter.remove();
            }
        }

    }

    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        list.add("aa");
        list.add(null);
        list.add(null);
        System.out.println(list);
        removeNull(list);
        System.out.println(list);

    }
}
