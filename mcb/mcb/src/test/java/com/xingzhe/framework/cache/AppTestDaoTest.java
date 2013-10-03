package com.xingzhe.framework.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cctc.framework.test.BaseTestCase;
import com.xingzhe.zhzs.task.dao.AppTestDao;
import com.xingzhe.zhzs.task.domain.AppTest;

public class AppTestDaoTest extends BaseTestCase {

	@Autowired
	private  AppTestDao appTestDao;
	
	@Test
	public void  test1(){
		AppTest appTest =new AppTest();
		appTest.setSid(1);
		appTest.setName("13221");
		appTestDao.add(appTest);
	}
}
