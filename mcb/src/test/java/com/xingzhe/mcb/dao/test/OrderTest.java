package com.xingzhe.mcb.dao.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xingzhe.framework.test.BaseTestCase;
import com.xingzhe.mcb.dao.OrderDao;
import com.xingzhe.mcb.domain.Order;

public class OrderTest extends BaseTestCase
{
    @Autowired
    private OrderDao<Order> orderDao;
    
    //@Test
    public void test1(){
        long l1=System.currentTimeMillis();
        orderDao.addorder(1, "1#2;2#2");
        long l2=System.currentTimeMillis();
        System.out.println(l2-l1);
    }
    
    @Test
    public void test2(){
        for(int i=0;i<100;i++){
//            new Thread(){
//
//                @Override
//                public void run()
//                {
                    orderDao.addorder(1, "1#2;2#2");
//                }
//                
//            }.start();
        }
    }
}
