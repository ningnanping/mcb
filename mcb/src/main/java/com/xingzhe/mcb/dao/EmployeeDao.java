package com.xingzhe.mcb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.mcb.mapper.EmployeeMapper;

@Repository
public class EmployeeDao {
	@Autowired
	private EmployeeMapper employeeMapper;
	

	public  List<SelectBoxObj> getAllEmployee(){
		return employeeMapper.getAllEmployee();
	}
}
