package com.xingzhe.zhzs.task.domain;

import com.xingzhe.framework.domain.BaseObj;

public class AppTest extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1955309597783792781L;

	private int id;
	private int sid;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
