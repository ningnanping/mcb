package com.xingzhe.common.service;

import java.util.List;

import com.xingzhe.common.domain.User;

public interface UserService {
	public  List<User> getUserByName(String userName);
}
