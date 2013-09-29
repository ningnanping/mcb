package com.xingzhe.zhzs.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cctc.framework.test.BaseTestCase;
import com.xingzhe.zhzs.dao.OracleTableDao;

public class OracleTableDaoTest extends BaseTestCase
{
    @Autowired
    public OracleTableDao oracleTableDao;
    
    @Test
    public void test1(){
        List<String> list=oracleTableDao.getList();
        for (String string : list)
        {
            if(oracleTableDao.getCount(string)==0){
                System.out.println(string);
                oracleTableDao.del(string);
            }
           
        }
    }
    
}
