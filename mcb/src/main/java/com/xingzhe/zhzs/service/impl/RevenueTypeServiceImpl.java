package com.xingzhe.zhzs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.zhzs.dao.RevenueTypeDao;
import com.xingzhe.zhzs.dao.redis.RevenueTypeRedisDao;
import com.xingzhe.zhzs.service.RevenueTypeService;

@Service("revenueTypeService")
public class RevenueTypeServiceImpl implements RevenueTypeService
{
    
    @Autowired
    private RevenueTypeDao revenueTypeDao;
    
    @Autowired
    private RevenueTypeRedisDao revenueTypeRedisDao;
    
    
    public List<SelectBoxObj> getAllRevenueType()
    {
        List<SelectBoxObj> list=revenueTypeRedisDao.queryTaxTypeList();
        if(list==null||list.size()==0){
            list=revenueTypeDao.getAllRevenueType();
            if(list!=null&&list.size()!=0){
                revenueTypeRedisDao.saveTaxType(list);
            }
        }
        return list;
    }
    
}
