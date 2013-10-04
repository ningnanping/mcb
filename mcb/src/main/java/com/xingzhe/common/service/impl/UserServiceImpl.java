package com.xingzhe.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.common.dao.UserDao;
import com.xingzhe.common.domain.User;
import com.xingzhe.common.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public  List<User> getUserByName(String userName){
		return userDao.getUserByName(userName);
	}
}
