package com.xingzhe.zhzs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.zhzs.mapper.RevenueTypeMapper;

/**
 * 税源种类
 * 
 * @author LuTang
 * 
 */
@Repository
public class RevenueTypeDao
{
    @Autowired
    private RevenueTypeMapper revenueTypeMapper;
    
    /**
     * 获取所有的税源种类
     * 
     * @return
     */
    public List<SelectBoxObj> getAllRevenueType()
    {
        return revenueTypeMapper.getAllRevenueType();
    }
    
   
    
}
