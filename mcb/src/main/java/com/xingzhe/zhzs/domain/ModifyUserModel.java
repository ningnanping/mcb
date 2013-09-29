/**
 * 
 */
package com.xingzhe.zhzs.domain;


/**
 * 修改密码 用户模型
 * 
 * @author hoopy
 *
 */
public class ModifyUserModel extends UserModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6911986962694839158L;

	/**
	 * 修改密码时新的密码
	 */
	private String newpwd;
	
	/**
	 * 修改密码时新的密码 repeat
	 */
	private String rnewpwd;

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getRnewpwd() {
		return rnewpwd;
	}

	public void setRnewpwd(String rnewpwd) {
		this.rnewpwd = rnewpwd;
	}

}
