package com.xingzhe.framework.test;

import org.junit.Test;

import com.xingzhe.framework.util.MD5Util;

public class PasswordTest
{
    @Test
    public  void test1()
    {
        System.out.println(MD5Util.getInstance().md5s(("yuangong1"+"123456").getBytes()));
        System.out.println(MD5Util.getInstance().md5s(("yuangong2"+"123456").getBytes()));
        System.out.println(MD5Util.getInstance().md5s(("yuangong3"+"123456").getBytes()));
        System.out.println(MD5Util.getInstance().md5s(("admin"+"admin").getBytes()));
    }
}
