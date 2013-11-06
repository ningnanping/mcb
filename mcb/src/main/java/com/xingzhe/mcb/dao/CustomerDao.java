package com.xingzhe.mcb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.mcb.domain.Customer;
import com.xingzhe.mcb.mapper.CustomerMapper;

@Repository
public class CustomerDao {
	@Autowired
	private CustomerMapper customerMapper;
	
	public List<Customer> selectCustomerListForPage(Map<String, Object> map){
		return customerMapper.selectCustomerListForPage(map);
	}

	public int selectCustomerListForCount(Map<String, Object> map){
		return customerMapper.selectCustomerListForCount(map);
	}
	
	public void  updateCustomer(Customer customer){
		customerMapper.updateCustomer(customer);
	}
	
	public void  delCustomer(int id){
		 customerMapper.delCustomer(id);
	}
	
	public void saveCustomer(Customer customer){
		 customerMapper.saveCustomer(customer);
	}
	
	public int getVipId(){
		return customerMapper.getVipId();
	}
}
