package com.xingzhe.mcb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.cache.redis.NeedRedisCached;
import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.mcb.dao.EmployeeDao;
import com.xingzhe.mcb.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	@NeedRedisCached(type="HASH",returnType=SelectBoxObj.class,endKey="COM.XINGZHE.MCB.DOMAIN.EMPLOYEE",isArray=true)
	public List<SelectBoxObj> getAllEmployee() {
		return  employeeDao.getAllEmployee();
	}

}
