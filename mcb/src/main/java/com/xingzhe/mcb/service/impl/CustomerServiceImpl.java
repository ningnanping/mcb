package com.xingzhe.mcb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.mcb.dao.CustomerDao;
import com.xingzhe.mcb.domain.Customer;
import com.xingzhe.mcb.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> selectCustomerListForPage(Map<String, Object> map) {
		return customerDao.selectCustomerListForPage(map);
	}

	@Override
	public int selectCustomerListForCount(Map<String, Object> map) {
		return customerDao.selectCustomerListForCount(map);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);

	}

	@Override
	public void delCustomer(int id) {
		customerDao.delCustomer(id);
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);
	}

	@Override
	public int getVipId() {
		return customerDao.getVipId();
	}

}
