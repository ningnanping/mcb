package com.xingzhe.mcb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.mcb.dao.EmployeeDao;
import com.xingzhe.mcb.dao.redis.EmployeeRedisDao;
import com.xingzhe.mcb.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeRedisDao employeeRedisDao;

	@Override
	public List<SelectBoxObj> getAllEmployee() {
		List<SelectBoxObj> list = employeeRedisDao.getAllEmployee();
        if (list == null || list.size() == 0) {
            list = employeeDao.getAllEmployee();
            if (list != null && list.size() != 0) {
                employeeRedisDao.saveAllEmployee(list);
            }
        }
        return list;
	}

}
