package com.xingzhe.zhzs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.zhzs.mapper.TableStyleTypeMapper;

@Repository
public class TableStyleTypeDao
{
    @Autowired
    private TableStyleTypeMapper tableStyleTypeMapper;
    
    /**
     * 根据税种获取获取表样
     * @param revenueTypeId
     * @return
     */
    public List<SelectBoxObj> getRevenueSampleByRevenueType(String revenueTypeId)
    {
        return tableStyleTypeMapper.getRevenueSampleByRevenueType(revenueTypeId);
    }
}
