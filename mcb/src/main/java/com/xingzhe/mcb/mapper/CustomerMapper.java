package com.xingzhe.mcb.mapper;

import java.util.List;
import java.util.Map;

import com.xingzhe.framework.mapper.SqlMapper;
import com.xingzhe.mcb.domain.Customer;

public interface CustomerMapper extends SqlMapper {

	List<Customer> selectCustomerListForPage(Map<String, Object> map);

	int selectCustomerListForCount(Map<String, Object> map);
}
