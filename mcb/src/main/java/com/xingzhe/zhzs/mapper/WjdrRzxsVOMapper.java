package com.xingzhe.zhzs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xingzhe.framework.mapper.SqlMapper;
import com.xingzhe.zhzs.domain.WjdrRzxsVO;

public interface WjdrRzxsVOMapper extends SqlMapper {
	
	/***********************************************/
	/***    导入日志查询                                                                      ***/
	/***            开始                                                                ***/
	/***********************************************/

	/**
	 * 导入日志查询
	 * 
	 * @param wjlx
	 *            文件类型
	 * @param start
	 *            开始记录id
	 * @param end
	 *            结束记录ID
	 * @return
	 */
	List<WjdrRzxsVO> getWjdrRzxsVOForPage(Map<String, Object> map);
	
	/**
	 * 根据文件类型获取导入总条数查询
	 * 
	 * @param wjlx
	 *            文件类型
	 * @param start
	 *            开始记录id
	 * @param end
	 *            结束记录ID
	 * @return
	 */
	int getTotalCount(Map<String, Object> map);
	
	/**
	 * 日志的添加
	 * 
	 * @param wjdrRzxsVO
	 */
	@Insert("insert into T_XT_WJDR_RZB (XH, BDPZ_XH, WJDR_SJ, DRS, CGS, SBS, ZT, LRRY_DM, LR_SJ,SJFW_DM,LWS,XG_SJ) values(#{xh},#{bdpzxh},#{drsjd},#{drs},#{cgs},#{sbs},#{zs},#{lrry},#{drsjd},#{sjfw},#{lws},#{drsjd})")
	void addWjdrRzxsVO(WjdrRzxsVO wjdrRzxsVO);
	

	/**
	 * 废弃该数据
	 * 用录入人员和导入时间修改该条记录的状态根据序号修改
	 */
	@Update("update t_xt_wjdr_rzb t set t.zt='3' , t.xgry_dm=#{lrry} , t.xg_sj=#{drsjd} where xh=#{xh}")
	void delWjdrRzxsVOLog(WjdrRzxsVO wjdrRzxsVO);

	
	
	/***********************************************/
	/***    导入日志查询                                                                      ***/
	/***            结束                                                                ***/
	/***********************************************/


	
	/***********************************************/
    /***    登记数据比对                    查询                                                  ***/
    /***            开始                                                                ***/
    /***********************************************/
	
	/**
	 * 登记数据对比分页查询
	 * 
	 * @param map
	 *            wjlx 文件类型 startDate 开始时间 endDate 结束时间 sfjg 是否加工 sjfw 编号
	 * 
	 *            start 开始行数 end 结束行数
	 * @return
	 */
	List<WjdrRzxsVO> sjdTyhjcxForPage(Map<String, Object> map);
	
	
	/**
     * 登记数据对比分页查询
     * 
     * @param map
     *            wjlx 文件类型 startDate 开始时间 endDate 结束时间 sfjg 是否加工 sjfw 编号
     * 
     *            start 开始行数 end 结束行数
     * @return
     */
	int sjdTyhjcxTotalCount(Map<String, Object> map);
	
	
	
	
	 /***********************************************/
    /***    登记数据比对               查询                                                        ***/
    /***            结束                                                               ***/
    /***********************************************/

	
	/**
	 * 税源数据对比
	 * 
	 * @param map
	 * 
	 *            wjlx 文件类型 #####必须使用 startDate 开始时间 endDate 结束时间 sjfw 编号
	 * 
	 *            start 开始行数 ###在用于分页查询必须使用 end 结束行数
	 * @return
	 */
	List<WjdrRzxsVO> sjgzbdSjgzbdcxYbdForPage(Map<String, Object> map);

	int sjgzbdSjgzbdcxYbdTotalCount(Map<String, Object> map);

	
	
	/**
	 * 文件类型
	 * 获取调用存储过程的名称
	 * @return
	 */
	@Select("select t.bdckgcmc from T_ZS_CS_XXBDPZ t where t.bdpz_xh=#{bdpzxh}")
	List<String> getBdckgcmc(@Param(value = "bdpzxh") String bdpzxh);
	
}
