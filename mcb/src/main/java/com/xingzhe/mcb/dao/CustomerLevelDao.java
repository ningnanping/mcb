package com.xingzhe.mcb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.mcb.mapper.CustomerLevelMapper;

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

    public List<SelectBoxObj> getAllCustomerLevel() {
        return customerLevelMapper.getAllCustomerLevel();
    }
}
