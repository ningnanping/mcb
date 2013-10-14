package com.xingzhe.common.service;

import java.util.List;

import com.xingzhe.common.domain.User;

public interface UserService
{
    List<User> getUserByName(String userName);
    
    boolean updatePassword(String password, String userName);
}
