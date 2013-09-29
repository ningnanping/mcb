package com.xingzhe.zhzs.dao.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cctc.framework.test.BaseTestCase;
import com.xingzhe.zhzs.dao.DepartmentDao;
import com.xingzhe.zhzs.dao.FileTypeDao;

public class DepartmentRedisDaoTest extends BaseTestCase{

	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
	FileTypeDao fileTypeDao;
	
	@Test
	public void  test1(){
		System.out.println(departmentDao.getAllDepartment());
	}
	
	@Test
	public void  test2(){
		System.out.println(fileTypeDao.getFileTypeByDepartmentId("03"));
	}
}
