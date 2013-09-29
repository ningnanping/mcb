package com.xingzhe.zhzs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.domain.CallableStatementObj;
import com.xingzhe.zhzs.dao.RegisterDataDao;
import com.xingzhe.zhzs.domain.WjdrRzxsVO;
import com.xingzhe.zhzs.service.RegisterDataService;

@Service("registerDataService")
public class RegisterDataServiceImpl implements RegisterDataService
{
    @Autowired
    private RegisterDataDao registerDataDao;
    
    public List<WjdrRzxsVO> sjdTyhjcxForPage(Map<String,Object> map)
    {
        return registerDataDao.sjdTyhjcxForPage(map);
    }
    
    public int sjdTyhjcxTotalCount(Map<String,Object> map)
    {
        return registerDataDao.sjdTyhjcxTotalCount(map);
    }

    /* (non-Javadoc)
     */
    public CallableStatementObj unifiedRegistration(String wjlx,String bdwjlx, String pcxh, String czrydm, String swjdm)
    {
        return registerDataDao.unifiedRegistration(wjlx,bdwjlx, pcxh, czrydm, swjdm);
    }
    
}
