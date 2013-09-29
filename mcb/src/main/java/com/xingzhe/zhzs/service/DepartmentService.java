package com.xingzhe.zhzs.service;

import java.util.List;

import com.xingzhe.framework.domain.SelectBoxObj;

public interface DepartmentService {
	
	List<SelectBoxObj>  getAllDepartment();
	
	List<SelectBoxObj> getDepartmentByRevenueTypeId(String revenueTypeId);

}
