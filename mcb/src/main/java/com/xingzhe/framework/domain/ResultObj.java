package com.xingzhe.framework.domain;

/**
 * 返回结果
 * @author LuTang
 *
 */
public class ResultObj extends BaseObj{

	private static final long serialVersionUID = 1L;
	
	private int code;
	
	private String  mess;

	public ResultObj(int code) {
		this.code = code;
		if(1000==this.code){
			this.mess="成功";
		}
	}

	public ResultObj(int code, String mess) {
		super();
		this.code = code;
		this.mess = mess;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
}
