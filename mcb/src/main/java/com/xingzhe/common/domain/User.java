package com.xingzhe.common.domain;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 用户操作类
 * 
 * @author LuTang
 * 
 */
public class User extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -602518080004018624L;

	/**
	 * 自动生成
	 */
	private int id;

	/**
	 * 用户注册的用户名 唯一存在
	 */
	private String userName;

	/**
	 * 用（用户名+明文密码）Md5加密后的32字符
	 */
	private String password;
	
	/**
	 * 删除标识
	 */
	private int isDel;
	
	private int isSystem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public int getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(int isSystem) {
		this.isSystem = isSystem;
	}

}
