package com.cctc.framework.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Title ：
 * @Description ：junit 单元测试的父类 没有事务回滚
 * @author ：LuFengLiang
 * @Company: 北京恒泰实达科技股份有限公司
 * @Copyright: Copyright (c) @2012-8-10
 * @version ： 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BaseTestCase {

}
