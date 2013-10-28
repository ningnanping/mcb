package com.xingzhe.mcb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.mcb.mapper.EmployeeTypeMapper;

@Repository
public class EmployeeTypeDao {
	@Autowired
	private EmployeeTypeMapper employeeTypeMapper;
	public List<SelectBoxObj> getAllEmployeeType(){
		return employeeTypeMapper.getAllEmployeeType();
	}
}
