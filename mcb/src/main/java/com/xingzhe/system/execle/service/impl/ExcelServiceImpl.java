package com.xingzhe.system.execle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.system.execle.dao.ExcelDao;
import com.xingzhe.system.execle.domain.Excel;
import com.xingzhe.system.execle.service.ExcelService;
import com.xinzhe.system.execle.dao.redis.ExcelRedisDao;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService{
	
	@Autowired
	private ExcelDao excelDao;
	
	@Autowired
	private ExcelRedisDao excelRedisDao;
	

	public List<Excel> getExcel(String id) {
		List<Excel> list=excelRedisDao.getExcel(id);
		if(list==null||list.size()==0){
			list=excelDao.getExcelList(id);
			if(list!=null&&list.size()!=0){
				excelRedisDao.saveExcel(id, list);
			}
		}
		return list;
	}



}
