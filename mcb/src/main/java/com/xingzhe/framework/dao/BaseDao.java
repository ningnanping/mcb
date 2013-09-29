package com.xingzhe.framework.dao;

import java.sql.SQLException;

/**
 * @Title ：
 * @Description ：基础的Dao服务类
 * @author ：LuFengLiang
 * @Copyright: Copyright (c) @2012-8-10
 * @version ： 1.0
 */
public abstract class BaseDao<T> {
	
	public void save(T entity)throws SQLException {
	}
	public void update(T entity)throws SQLException {
	}
	public void del(T entity)throws SQLException {
	}
	public void del(int id)throws SQLException {
	}
	public void del(String id)throws SQLException {
	}
}
