package com.xingzhe.mcb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.mcb.dao.EmployeeTypeDao;
import com.xingzhe.mcb.dao.redis.EmployeeTypeRedisDao;
import com.xingzhe.mcb.service.EmployeeTypeService;

@Service("employeeTypeService")
public class EmployeeTypeServiceImpl implements EmployeeTypeService {

	@Autowired
	private EmployeeTypeDao employeeTypeDao;
	
	@Autowired
	private EmployeeTypeRedisDao employeeTypeRedisDao;
	
	@Override
	public List<SelectBoxObj> getAllEmployeeType() {
		List<SelectBoxObj> list=employeeTypeRedisDao.getAllEmployeeType();
		if(list==null||list.size()==0){
			list=employeeTypeDao.getAllEmployeeType();
			if(list!=null&&list.size()!=0){
				employeeTypeRedisDao.savetAllEmployeeType(list);
			}
		}
		return list;
	}

}
