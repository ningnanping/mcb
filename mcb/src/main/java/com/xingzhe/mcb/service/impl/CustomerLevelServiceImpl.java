package com.xingzhe.mcb.service;

import com.xingzhe.mcb.dao.CustomerLevelDao;
import com.xingzhe.mcb.domain.CustomerLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: LuTang
 * Date: 13-10-9
 * Time: 下午7:34
 * To change this template use File | Settings | File Templates.
 */

@Service("customerLevelService")
public class CustomerLevelServiceImpl implements CustomerLevelService {

    @Autowired
    private CustomerLevelDao customerLevelDao;

    @Override
    public List<CustomerLevel> getAllCustomerLevel() {
        return customerLevelDao.getAllCustomerLevel();
    }
}
