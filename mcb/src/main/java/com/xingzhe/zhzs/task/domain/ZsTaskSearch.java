package com.xingzhe.zhzs.task.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

public class ZsTaskSearch extends BaseObj {

	private static final long serialVersionUID = 1817790848096121800L;
	
	// 任务标题
	private String title;
	
	private String startDate;
	
	private String endDate;
	
	private String state;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
