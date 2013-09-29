package com.xingzhe.zhzs.dao.test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cctc.framework.test.BaseTestCase;
import com.xingzhe.framework.dao.redis.BaseRedisDao;
import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.zhzs.dao.redis.DepartmentRedisDao;
import com.xinzhe.framework.cache.redis.RedisCache;

public class DepartmentDaoTest extends BaseTestCase{

	@Autowired
	DepartmentRedisDao departmentRedisDao;
	
	@Autowired
	RedisCache redisCache;
	
	List<SelectBoxObj> depList = new ArrayList<SelectBoxObj>();
	int num = 10;
	
	@Before
	public void setUp(){
		SelectBoxObj sbo = null;
		for (int i = 0;i < num;i++){
			sbo = new SelectBoxObj();
			sbo.setId(String.valueOf(i));
			sbo.setName(String.valueOf(i) + "Name");
			
			depList.add(sbo);
		}
	}
	
	
	@Test
	public void  departmentTestCase(){
		boolean saveFlag = departmentRedisDao.saveDepartmentList(depList);
		assertTrue(saveFlag);
		
		List<SelectBoxObj> readList = departmentRedisDao.queryDepartmentList();
		assertSame(num,readList.size());

	}
	
	@After
    public void tearDown() throws Exception {
		redisCache.remove(BaseRedisDao.REDIS_KEY_PREFIX_DEPARTMENT);
    }

}
