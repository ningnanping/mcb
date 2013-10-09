package com.xingzhe.mcb.service;

import com.xingzhe.framework.test.BaseTestCase;
import com.xingzhe.mcb.service.CustomerLevelService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: LuTang
 * Date: 13-10-9
 * Time: 下午9:29
 * To change this template use File | Settings | File Templates.
 */
public class CustomerLevelServiceTest extends BaseTestCase {

    @Autowired
    public CustomerLevelService customerLevelService;

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetAllCustomerLevel() throws Exception {
        System.out.println(customerLevelService.getAllCustomerLevel());
    }
}
