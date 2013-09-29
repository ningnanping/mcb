/**
 * 
 */
package com.xingzhe.zhzs.domain;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 用户模型
 * 
 * @author hoopy
 *
 */
public class UserModel extends BaseObj {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7960401016530214884L;

	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 登录用户名
	 */
	private String user;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 用户名称
	 */
	private String username;
	
	/**
	 * 登录认证类型
	 */
	private String authType;
	
	/**
	 * 用户描述
	 */
	private String desUser;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getDesUser() {
		return desUser;
	}

	public void setDesUser(String desUser) {
		this.desUser = desUser;
	}

}
