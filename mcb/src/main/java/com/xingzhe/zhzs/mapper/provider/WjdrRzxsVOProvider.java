package com.xingzhe.zhzs.mapper.provider;

import com.xingzhe.framework.mapper.provider.BaseSqlProvider;

/**
 * SQL 生成工具
 * 
 * @author Administrator
 * 
 */
public class WjdrRzxsVOProvider extends BaseSqlProvider {
	private static final String WJDRRZXSVOFORPAGE = "select jg.mc_j sjfw_dm,to_char(t.lr_sj, 'yyyy-mm-dd') lrsj,t.drs,ry.mc,t.xh,"
			+ "t.bdpz_xh,lx.wjlx_mc,bm.mc bmmc,CASE  when t.zt = '1' then   '未统一户籍'" + "  when t.zt = '4' then   '已统一户籍'  when t.zt = '5' then   '已比对'  else   '导入异常'"
			+ "end pdzt,t.zt,t.cgs,t.sbs,t.lws  from t_xt_wjdr_rzb  t,t_dm_gy_swjg   jg,"
			+ "t_dm_gy_swry   ry,t_xt_wjdr_wjlx lx,t_zs_dm_wbmnb  bm where t.zt <> '3'   and t.bdpz_xh like #{wjlx}|| '%'"
			+ "   and t.lrry_dm = ry.swry_dm   and t.bdpz_xh = lx.wjlx_dm   and lx.cjbm_dm = bm.bm_dm   and t.sjfw_dm = jg.swjg_dm(+)";

	//private static final String WJDRRZXSVOFORPAGE_WHERE = "and t.sjfw_dm=#{sjfw}";

	public String getListWjdrRzxsVOForPageByWjlxSql() {
	
			return forPage(WJDRRZXSVOFORPAGE);
		// if (!"".equals(sjfw) && sjfw != null&&!sjfw.equals("12410000000")) {
		// } else {
		
		// }
	}

	public String getTotalCountSizeWjdrRzxsVOForPageSql() {
		// if (!"".equals(sjfw) && sjfw != null&&!sjfw.equals("12410000000")) {
		// return getTotalCount(WJDRRZXSVOFORPAGE, WJDRRZXSVOFORPAGE_WHERE);
		// } else {
		return getTotalCount(WJDRRZXSVOFORPAGE);
		// }
	}
}
