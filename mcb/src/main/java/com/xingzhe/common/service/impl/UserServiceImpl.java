package com.xingzhe.common.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.common.dao.UserDao;
import com.xingzhe.common.domain.User;
import com.xingzhe.common.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService
{
    
    private static final  Logger log = LoggerFactory.getLogger(UserServiceImpl.class);  
    @Autowired
    private UserDao userDao;
    
    
    public List<User> getUserByName(String userName)
    {
        	return  userDao.getUserByName(userName);
    }
    
    @Override
    public boolean updatePassword(String password, String userName)
    {
        try
        {
            userDao.updatePassword(password, userName);
        }
        catch (Exception e)
        {
            log.error(e.toString()); 
            return false;
        }
		return true;
    }
}
