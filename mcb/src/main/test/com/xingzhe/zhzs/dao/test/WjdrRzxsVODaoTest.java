package com.xingzhe.zhzs.dao.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cctc.framework.test.BaseTestCase;
import com.xingzhe.system.execle.dao.ExcelDao;
import com.xingzhe.system.execle.util.ExcelOperateUtil;
import com.xingzhe.zhzs.dao.WjdrRzxsVODao;
import com.xingzhe.zhzs.domain.WjdrRzxsVO;
import com.xingzhe.framework.util.DateUtil;

public class WjdrRzxsVODaoTest extends BaseTestCase {

	@Autowired
	private WjdrRzxsVODao<WjdrRzxsVO> wjdrRzxsVODao;

	@Autowired
	private ExcelDao wjdrExcelDao;
	
	@Autowired
	ExcelOperateUtil excelOperateUtil;

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

	@Test
	public void test3() {
		
		File file = new File("D:/1.xls");
		
		Map<String, String> systemMap = new HashMap<String, String>();
		systemMap.put("ssq", DateUtil.dateToStr(new Date(),DateUtil.YYYY_MM_DD));
		systemMap.put("strWjlx", "T999");
		systemMap.put("GUID", UUID.randomUUID().toString());
		systemMap.put("czrydm", "CZ00005");
		systemMap.put("sjfw", "");
		try {
			excelOperateUtil.readfile(null, systemMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(wjdrExcelDao.getWjdrExcelList("J016"));
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
