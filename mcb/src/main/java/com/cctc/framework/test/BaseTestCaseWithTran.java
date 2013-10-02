package com.cctc.framework.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title ：
 * @Description ：junit   单元测试的父类  带有 事务回滚的功能
 * @author ：LuFengLiang
 * @Copyright: Copyright (c) @2012-8-10
 * @version ： 1.0
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class BaseTestCaseWithTran
{
    
}
