package com.xingzhe.mcb.service;

import java.util.List;
import java.util.Map;

import com.xingzhe.mcb.domain.Customer;

public interface CustomerService {
	List<Customer> selectCustomerListForPage(Map<String, Object> map);

	int selectCustomerListForCount(Map<String, Object> map);
	
	void  updateCustomer(Customer customer);
	
	void  delCustomer(int id);
	
	void saveCustomer(Customer customer);
	
	int getVipId();
}
