package com.xingzhe.zhzs.dao.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cctc.framework.test.BaseTestCase;
import com.xingzhe.zhzs.dao.WjdrRzxsVODao;
import com.xingzhe.zhzs.domain.WjdrRzxsVO;

public class WjdrRzxsVODaoTest extends BaseTestCase {

	@Autowired
	private WjdrRzxsVODao<WjdrRzxsVO> wjdrRzxsVODao;


	// @Test
	public void test1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wjlx", "");
		map.put("start", 0);
		map.put("end", 10);
		map.put("sas", "21312");
		System.out.println(wjdrRzxsVODao.wjdrRzxsVOForPageByWjlxSql(map).size());
	}

	// @Test
	public void test2() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wjlx", "");
		System.out.println(wjdrRzxsVODao.getTotalCount(map));
	}

	//@Test
	public void  test4(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wjlx", "");
		map.put("start", 0);
		map.put("end", 10);
		map.put("startDate","");
		map.put("endDate","2013-12-12");
		map.put("sfjg","1");
		map.put("sjfw","1241000000");
		System.out.println(wjdrRzxsVODao.sjdTyhjcxForPage(map));
	}

	
	//@Test
	public  void test5(){
		
		System.out.println(wjdrRzxsVODao.comparisonData("CJBD015",  "2013-08-01", "2015-08-01","CZ00005"));
	}
}
