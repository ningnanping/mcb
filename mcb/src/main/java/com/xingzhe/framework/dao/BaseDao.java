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
	
	/**
	 * 保存
	 * @param entity
	 * @throws SQLException
	 */
	public void save(T entity)throws SQLException {
	}
	
	/**
	 * 更新
	 * @param entity
	 * @throws SQLException
	 */
	public void update(T entity)throws SQLException {
	}
	/**
	 * 删除 根据对象（复杂对象，int，String 都可以）
	 * @param entity
	 * @throws SQLException
	 */
	public void del(Object entity)throws SQLException {
	}
}
