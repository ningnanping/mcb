package com.xingzhe.framework.domain;


/**
 * 下拉列表框
 * @author LuTang
 *
 */
public class SelectBoxObj extends BaseObj {

	private static final long serialVersionUID = -1129725639111648194L;

	/**
	 * ID
	 */
	private int id;

	/**
	 * 名称
	 */
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
