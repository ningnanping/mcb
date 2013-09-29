package com.xingzhe.zhzs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.zhzs.dao.DepartmentDao;
import com.xingzhe.zhzs.dao.redis.DepartmentRedisDao;
import com.xingzhe.zhzs.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService
{
    
    @Autowired
    private DepartmentDao departmentdao;
    
    @Autowired
    private DepartmentRedisDao departmentRedisDao;
    
    public List<SelectBoxObj> getAllDepartment()
    {
        List<SelectBoxObj> list = departmentRedisDao.queryDepartmentList();
        if (list == null || list.size() == 0)
        {
            list = departmentdao.getAllDepartment();
            if (list != null && list.size() != 0)
            {
                departmentRedisDao.saveDepartmentList(list);
            }
        }
        return list;
    }
    
    public List<SelectBoxObj> getDepartmentByRevenueTypeId(String revenueTypeId)
    {
        List<SelectBoxObj> list= departmentRedisDao.getDepartmentByRevenueTypeId(revenueTypeId);
        if(list==null||list.size()==0){
            list=departmentdao.getDepartmentByRevenueTypeId(revenueTypeId);
            if(list!=null&&list.size()!=0){
                departmentRedisDao.saveDepartmentByRevenueTypeId(list, revenueTypeId);
            }
        }
        return list;
    }
    
}
