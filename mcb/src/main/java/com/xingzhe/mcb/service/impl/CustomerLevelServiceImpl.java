package com.xingzhe.mcb.service.impl;

import com.xingzhe.mcb.dao.CustomerLevelDao;
import com.xingzhe.mcb.dao.redis.CustomerLevelRedisDao;
import com.xingzhe.mcb.domain.CustomerLevel;
import com.xingzhe.mcb.service.CustomerLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private CustomerLevelRedisDao customerLevelRedisDao;

    /**
     * 在缓存中
     * */
    @Override
    public List<CustomerLevel> getAllCustomerLevel() {
        List<CustomerLevel> list = customerLevelRedisDao.getAllCustomerLevel();
        if (list == null || list.size() == 0) {
            list = customerLevelDao.getAllCustomerLevel();
            if (list != null && list.size() != 0) {
                customerLevelRedisDao.saveAllCustomerLevel(list);
            }
        }
        return list;
    }

}
