package com.xingzhe.common.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.common.dao.UserDao;
import com.xingzhe.common.domain.User;
import com.xingzhe.common.dao.redis.UserRedisDao;
import com.xingzhe.common.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService
{
    
    private static final  Logger log = LoggerFactory.getLogger(UserServiceImpl.class);  
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserRedisDao userRedisDao;
    
    public List<User> getUserByName(String userName)
    {
        List<User> list = userRedisDao.getUserList(userName);
        if (list == null || list.size() == 0)
        {
            list = userDao.getUserByName(userName);
            if (list != null && list.size() != 0)
            {
                userRedisDao.saveUserList(userName, list);
            }
        }
        return list;
    }
    
    @Override
    public boolean updatePassword(String password, String userName)
    {
        try
        {
            userDao.updatePassword(password, userName);
            List<User> list=userRedisDao.getUserList(userName);
            if(list!=null&&list.size()!=0){
                list.get(0).setPassword(password);
                userRedisDao.saveUserList(userName, list);
            }else{
                return false;
            }
            return true;
        }
        catch (Exception e)
        {
            log.error(e.toString()); 
            return false;
        }
    }
}
