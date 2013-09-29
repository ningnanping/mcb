package com.xingzhe.zhzs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.zhzs.mapper.DepartmentMapper;

@Repository
public class DepartmentDao {
	@Autowired
	private DepartmentMapper departmentMapper;

	public List<SelectBoxObj> getAllDepartment() {
		return departmentMapper.getAllDepartment();
	}

	
	/**
	 * 根据税源Id 获取 部门
	 * @param revenueTypeId
	 * @return
	 */
	public List<SelectBoxObj> getDepartmentByRevenueTypeId(String revenueTypeId){
	    return departmentMapper.getDepartmentByRevenueTypeId(revenueTypeId);
	}
}
