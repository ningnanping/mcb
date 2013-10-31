package com.xingzhe.framework.domain;


public class ActionStatus extends BaseObj {

	public ActionStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	private static final long serialVersionUID = 1817812001474539793L;
	private int code;
	private String desc;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
