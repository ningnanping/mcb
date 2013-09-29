package com.xingzhe.zhzs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.zhzs.dao.TableStyleTypeDao;
import com.xingzhe.zhzs.dao.redis.TableStyleTypeRedisDao;
import com.xingzhe.zhzs.service.TableStyleTypeService;

@Service("tableStyleTypeService")
public class TableStyleTypeServiceImpl implements TableStyleTypeService
{
    @Autowired
    private TableStyleTypeDao tableStyleTypeDao;
    
    @Autowired
    private TableStyleTypeRedisDao tableStyleTypeRedisDao;
    
    
    public List<SelectBoxObj> getRevenueSampleByRevenueType(String revenueTypeId)
    {
        List<SelectBoxObj> list=tableStyleTypeRedisDao.getTableTypeByTaxId(revenueTypeId);
        if(list==null||list.size()==0){
            list=tableStyleTypeDao.getRevenueSampleByRevenueType(revenueTypeId);
            if(list!=null&&list.size()!=0){
                tableStyleTypeRedisDao.saveTableTypeByTaxId(list, revenueTypeId);
            }
        }
        return list;
    }
    
}
