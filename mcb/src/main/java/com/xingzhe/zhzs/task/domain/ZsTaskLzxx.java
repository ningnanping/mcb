package com.xingzhe.zhzs.task.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;
import com.xingzhe.framework.util.DateUtil;

public class ZsTaskLzxx extends BaseObj {

	private static final long serialVersionUID = -3466396296382752145L;

	// 1、任务实例号
	private String rwslh;
	// 3、办理人员代码
	private String blryDm;
	// 4、办理人员名称
	private String blryMc;
	// 5、办理人税务机构代码
	private String blrswjgDm;
	// 6、办理人税务机构名称
	private String blrswjgMc;
	// 7、是否阅读
	private String isread = "0";
	// 8、办理日期
	private Date blDate;
	// 9、审核审批意见
	private String shspYj;
	// 10、环节标记
	private String hjbj;

	private String url = "";
	
	private String sj;

	public String getRwslh() {
		return rwslh;
	}

	public void setRwslh(String rwslh) {
		this.rwslh = rwslh;
	}

	public String getBlryDm() {
		return blryDm;
	}

	public void setBlryDm(String blryDm) {
		this.blryDm = blryDm;
	}

	public String getBlryMc() {
		return blryMc;
	}

	public void setBlryMc(String blryMc) {
		this.blryMc = blryMc;
	}

	public String getBlrswjgDm() {
		return blrswjgDm;
	}

	public void setBlrswjgDm(String blrswjgDm) {
		this.blrswjgDm = blrswjgDm;
	}

	public String getBlrswjgMc() {
		return blrswjgMc;
	}

	public void setBlrswjgMc(String blrswjgMc) {
		this.blrswjgMc = blrswjgMc;
	}

	public String getIsread() {
		return isread;
	}

	public void setIsread(String isread) {
		this.isread = isread;
	}

	public Date getBlDate() {
		return blDate;
	}

	public void setBlDate(Date blDate) {
		this.blDate = blDate;
	}

	public String getShspYj() {
		return shspYj;
	}

	public void setShspYj(String shspYj) {
		this.shspYj = shspYj;
	}

	public String getHjbj() {
		return hjbj;
	}

	public void setHjbj(String hjbj) {
		this.hjbj = hjbj;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSj() {
		sj = DateUtil.dateToStr(getBlDate(), DateUtil.YYYY_MM_DD);
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

}
