package com.xingzhe.zhzs.service;

import java.util.List;

import com.xingzhe.framework.domain.SelectBoxObj;

public interface TableStyleTypeService
{
    
    /**
     * 根据税种Id查找表样Id
     * 
     * @return
     */
    List<SelectBoxObj> getRevenueSampleByRevenueType(String revenueTypeId);
    
}
