package com.xingzhe.zhzs.task.domain;

import com.xingzhe.framework.domain.BaseObj;

public class UserDept extends BaseObj {

	private static final long serialVersionUID = -7038517110939198904L;
	
	private String rydm;
	private String rymc;
	private String jgdm;
	private String jgmc;
	private String sjswjgdm; // 上次税务机构代码，用来既定流程
	private String combory;

	public String getRydm() {
		return rydm;
	}

	public void setRydm(String rydm) {
		this.rydm = rydm;
	}

	public String getRymc() {
		return rymc;
	}

	public void setRymc(String rymc) {
		this.rymc = rymc;
	}

	public String getJgdm() {
		return jgdm;
	}

	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getSjswjgdm() {
		return sjswjgdm;
	}

	public void setSjswjgdm(String sjswjgdm) {
		this.sjswjgdm = sjswjgdm;
	}

	public String getCombory() {
		combory = getRydm() + " " + getRymc();
		return combory;
	}

	public void setCombory(String combory) {
		this.combory = combory;
	}
	
}
