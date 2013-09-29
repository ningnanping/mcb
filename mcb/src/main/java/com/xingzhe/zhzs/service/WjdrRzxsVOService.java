package com.xingzhe.zhzs.service;

import java.util.List;
import java.util.Map;

import com.xingzhe.zhzs.domain.WjdrRzxsVO;

public interface WjdrRzxsVOService {
	
	/*########################  导入日志查询#################*
	/**
	 * 日志分页
	 * @param map
	 * @return
	 */
	List<WjdrRzxsVO> getImportDataLog(Map<String,Object> map);

	/**
	 * 日志总数
	 * @param map
	 * @return
	 */
	int getImportDataLogCount(Map<String,Object> map);
	
	
	
	/***
	 * 添加数据
	 * 今天无数据
	 **/
	void hasNodataAdd(String xh,String wjlx,String sjfw,String lrry);
	
	
	/**
	 * 正常的日志
	 * @param xh
	 * @param wjlx
	 * @param sjfw
	 * @param lrry
	 * @param drs
	 * @param cgs
	 * @param sbs
	 * @param zs 状态
	 * @param lws 例外数
	 */
	void addImportDataLog(String xh,String wjlx,String sjfw,String lrry,String drs,String cgs,String sbs,String zs,String lws);
	
	
	/**
	 * 废弃该记录
	 * @param lrry
	 * @param xh
	 */
	void  delWjdrRzxsVOLog(String lrry,String xh);
	
}
