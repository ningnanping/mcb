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
	
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	public  List<User> getUserByName(String userName){
		return userMapper.getUserByName(userName);
	}

    /**
     * 更新密码
     * @param password
     * @param userName
     */
    public void  updatePassword(String password,String userName){
        userMapper.updatePassword(password, userName);
    }

}
