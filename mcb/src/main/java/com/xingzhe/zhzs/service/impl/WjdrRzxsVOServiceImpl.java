package com.xingzhe.zhzs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.zhzs.dao.WjdrRzxsVODao;
import com.xingzhe.zhzs.domain.WjdrRzxsVO;
import com.xingzhe.zhzs.service.WjdrRzxsVOService;

@Service("wjdrRzxsVOService")
public class WjdrRzxsVOServiceImpl implements WjdrRzxsVOService {
	
	@Autowired 
	private  WjdrRzxsVODao<WjdrRzxsVO> wjdrRzxsVOdao;
	
	
	/* (non-Javadoc)
	 */
	public  List<WjdrRzxsVO> getImportDataLog(Map<String,Object> map){
		return wjdrRzxsVOdao.wjdrRzxsVOForPageByWjlxSql(map);
	}
	public int getImportDataLogCount(Map<String,Object> map) {
		return wjdrRzxsVOdao.getTotalCount(map);
	}
	
	
	
	/* (non-Javadoc)
	 *今天无数据
	 */
	public void hasNodataAdd(String xh,String wjlx,String sjfw,String lrry) {
		WjdrRzxsVO wjdrRzxsVO=new WjdrRzxsVO();
		wjdrRzxsVO.setXh(xh);
		wjdrRzxsVO.setBdpzxh(wjlx);
		Date d=new Date();
		wjdrRzxsVO.setDrsjd(d);
		wjdrRzxsVO.setSjfw(sjfw);
		wjdrRzxsVO.setLrry(lrry);
		wjdrRzxsVO.setDrs("0");
		wjdrRzxsVO.setCgs("0");
		wjdrRzxsVO.setSbs("0");
		wjdrRzxsVO.setZs("1");
		wjdrRzxsVO.setLws("0");
		wjdrRzxsVOdao.addWjdrRzxsVO(wjdrRzxsVO);
		
	}
	/* (non-Javadoc)
	 */
	public void delWjdrRzxsVOLog(String lrry, String xh) {
		WjdrRzxsVO wjdrRzxsVO=new WjdrRzxsVO();
		wjdrRzxsVO.setXh(xh);
		wjdrRzxsVO.setLrry(lrry);
		wjdrRzxsVO.setDrsjd(new Date());
		wjdrRzxsVOdao.delWjdrRzxsVOLog(wjdrRzxsVO);
	}
	
	
	/* (non-Javadoc)
	 * 添加数据
	 */
	public void addImportDataLog(String xh,String wjlx,String sjfw,String lrry,String drs,String cgs,String sbs,String zs,String lws) {
		WjdrRzxsVO wjdrRzxsVO=new WjdrRzxsVO();
		wjdrRzxsVO.setXh(xh);
		wjdrRzxsVO.setBdpzxh(wjlx);
		Date d=new Date();
		wjdrRzxsVO.setDrsjd(d);
		wjdrRzxsVO.setSjfw(sjfw);
		wjdrRzxsVO.setLrry(lrry);
		wjdrRzxsVO.setDrs(drs);
		wjdrRzxsVO.setCgs(cgs);
		wjdrRzxsVO.setSbs(sbs);
		wjdrRzxsVO.setZs(zs);
		wjdrRzxsVO.setLws(lws);
		wjdrRzxsVOdao.addWjdrRzxsVO(wjdrRzxsVO);
	}
	
	
}
