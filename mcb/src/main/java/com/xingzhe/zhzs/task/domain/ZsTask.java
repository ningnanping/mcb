package com.xingzhe.zhzs.task.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;
import com.xingzhe.framework.util.DateUtil;

public class ZsTask extends BaseObj {

	private static final long serialVersionUID = 1817790848096121800L;
	
	private int id;
	// 1、任务实例号
	private String rwslh;
	// 2、任务标题
	private String title;
	// 3、受理人员代码
	private String slryDm;
	// 4、受理税务机关代码
	private String slswjgDm;
	// 5、附件URL
	private String fjurl = "";
	// 6、任务状态
	private String state = "1";
	private String stateStr;
	// 7、任务录入日期
	private Date lrSj;
	
	private String sj;
	
	private String isread;

	public String getRwslh() {
		return rwslh;
	}

	public void setRwslh(String rwslh) {
		this.rwslh = rwslh;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlryDm() {
		return slryDm;
	}

	public void setSlryDm(String slryDm) {
		this.slryDm = slryDm;
	}

	public String getSlswjgDm() {
		return slswjgDm;
	}

	public void setSlswjgDm(String slswjgDm) {
		this.slswjgDm = slswjgDm;
	}

	public String getFjurl() {
		return fjurl;
	}

	public void setFjurl(String fjurl) {
		this.fjurl = fjurl;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getLrSj() {
		return lrSj;
	}

	public void setLrSj(Date lrSj) {
		this.lrSj = lrSj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getSj() {
		sj = DateUtil.dateToStr(getLrSj(), DateUtil.YYYY_MM_DD);
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getIsread() {
		return isread;
	}

	public void setIsread(String isread) {
		this.isread = isread;
	}

}
