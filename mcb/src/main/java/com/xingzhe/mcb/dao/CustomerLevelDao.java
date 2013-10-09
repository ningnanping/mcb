package com.xingzhe.mcb.dao;

import com.xingzhe.mcb.domain.CustomerLevel;
import com.xingzhe.mcb.mapper.CustomerLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: LuTang
 * Date: 13-10-9
 * Time: 下午7:19
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CustomerLevelDao {

    @Autowired
    private CustomerLevelMapper customerLevelMapper;

    public List<CustomerLevel> getAllCustomerLevel() {
        return customerLevelMapper.getAllCustomerLevel();
    }
}
