package com.xingzhe.zhzs.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 数据 导入日志 基础类
 * 
 * @author lufengliang
 * 
 */
public class WjdrRzxsVO extends BaseObj {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 序号;
	private String xh;
	// 导入时间;
	private String drsj;
	// 导入条数;
	private String drs;
	// 录入人员;
	private String lrry;
	// 导入机关
	private String sjfw;

	// 新添加始
	// 部门名称
	private String bmmc;
	// 新添加终

	// 文件类型;
	private String wjlx;

	// 序号（对应各业务表中的导入批次号）;
	private String pcxh;

	// 比对配置序号;
	private String bdpzxh;

	// 状态:0开始，1正常结束，2非正常结束，3按序号已删除全部业务数据;
	private String zt;
	// 配对状态说明
	private String pdzt;

	// 配对状态说明
	private String zs;

	private String qdcgs;
	private String wxpds;
	private String pdczs;
	private String wfpds;

	private String jgsjq;
	private String jgsjz;

	// 例外数;
	private String lws;

	// 失败数;
	private String sbs;

	// 成功数;
	private String cgs;
	
	
	private Date drsjd;
	
	
	private String  wfds;

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getDrsj() {
		return drsj;
	}

	public void setDrsj(String drsj) {
		this.drsj = drsj;
	}

	public String getDrs() {
		return drs;
	}

	public void setDrs(String drs) {
		this.drs = drs;
	}

	public String getLrry() {
		return lrry;
	}

	public void setLrry(String lrry) {
		this.lrry = lrry;
	}

	public String getSjfw() {
		return sjfw;
	}

	public void setSjfw(String sjfw) {
		this.sjfw = sjfw;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getWjlx() {
		return wjlx;
	}

	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}

	public String getPcxh() {
		return pcxh;
	}

	public void setPcxh(String pcxh) {
		this.pcxh = pcxh;
	}

	public String getBdpzxh() {
		return bdpzxh;
	}

	public void setBdpzxh(String bdpzxh) {
		this.bdpzxh = bdpzxh;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getPdzt() {
		return pdzt;
	}

	public void setPdzt(String pdzt) {
		this.pdzt = pdzt;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	public String getQdcgs() {
		return qdcgs;
	}

	public void setQdcgs(String qdcgs) {
		this.qdcgs = qdcgs;
	}

	public String getWxpds() {
		return wxpds;
	}

	public void setWxpds(String wxpds) {
		this.wxpds = wxpds;
	}

	public String getPdczs() {
		return pdczs;
	}

	public void setPdczs(String pdczs) {
		this.pdczs = pdczs;
	}

	public String getWfpds() {
		return wfpds;
	}

	public void setWfpds(String wfpds) {
		this.wfpds = wfpds;
	}

	public String getJgsjq() {
		return jgsjq;
	}

	public void setJgsjq(String jgsjq) {
		this.jgsjq = jgsjq;
	}

	public String getJgsjz() {
		return jgsjz;
	}

	public void setJgsjz(String jgsjz) {
		this.jgsjz = jgsjz;
	}

	public String getLws() {
		return lws;
	}

	public void setLws(String lws) {
		this.lws = lws;
	}

	public String getSbs() {
		return sbs;
	}

	public void setSbs(String sbs) {
		this.sbs = sbs;
	}

	public String getCgs() {
		return cgs;
	}

	public void setCgs(String cgs) {
		this.cgs = cgs;
	}

	public Date getDrsjd() {
		return drsjd;
	}

	public void setDrsjd(Date drsjd) {
		this.drsjd = drsjd;
	}

    public String getWfds()
    {
        return wfds;
    }

    public void setWfds(String wfds)
    {
        this.wfds = wfds;
    }
	
	
}
