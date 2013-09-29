package com.xingzhe.zhzs.service;

import java.util.List;

import com.xingzhe.framework.domain.SelectBoxObj;

public interface RevenueTypeService
{
    /**
     * 根据sql 查询出所有的税种
     * @return
     */
    List<SelectBoxObj> getAllRevenueType();
}
