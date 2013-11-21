package com.xingzhe.mcb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.cache.redis.NeedRedisCached;
import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.mcb.dao.CustomerLevelDao;
import com.xingzhe.mcb.service.CustomerLevelService;

/**
 * Created with IntelliJ IDEA.
 * User: 陆凤良
 * Date: 13-10-9
 * Time: 下午7:34
 * 公司：三江行者工作室
 * To change this template use File | Settings | File Templates.
 */
@Service("customerLevelService")
public class CustomerLevelServiceImpl implements CustomerLevelService {

    @Autowired
    private CustomerLevelDao customerLevelDao;

    /**
     * 在缓存中
     * */
    @Override
    @NeedRedisCached(type="HASH",returnType=SelectBoxObj.class,endKey="COM.XINGZHE.MCB.DOMAIN.CUSTOMERLEVEL",isArray=true)
    public List<SelectBoxObj> getAllCustomerLevel() {
    	 return customerLevelDao.getAllCustomerLevel();
    }

}
