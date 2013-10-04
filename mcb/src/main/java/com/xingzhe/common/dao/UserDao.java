package com.xingzhe.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.common.domain.User;
import com.xingzhe.common.mapper.UserMapper;

@Repository
public class UserDao {
	
	@Autowired
	private UserMapper userMapper;
	
	public  List<User> getUserByName(String userName){
		return userMapper.getUserByName(userName);
	}

}
