package com.xingzhe.mcb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.cache.redis.NeedRedisCached;
import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.mcb.dao.EmployeeTypeDao;
import com.xingzhe.mcb.service.EmployeeTypeService;

@Service("employeeTypeService")
public class EmployeeTypeServiceImpl implements EmployeeTypeService {

	@Autowired
	private EmployeeTypeDao employeeTypeDao;

	@Override
	@NeedRedisCached(type="HASH",returnType=SelectBoxObj.class,endKey="COM.XINGZHE.MCB.DOMAIN.EMPLOYEE.TYPE",isArray=true)
	public List<SelectBoxObj> getAllEmployeeType() {
		return employeeTypeDao.getAllEmployeeType();
	}

}
