package com.xingzhe.zhzs.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.dao.BaseDao;
import com.xingzhe.framework.domain.CallableStatementObj;
import com.xingzhe.zhzs.domain.WjdrRzxsVO;
import com.xingzhe.zhzs.mapper.WjdrRzxsVOMapper;

@Repository
public class WjdrRzxsVODao<T extends WjdrRzxsVO> extends BaseDao<T> {

	private static final Logger log = LoggerFactory.getLogger(WjdrRzxsVODao.class);

	@Autowired
	private WjdrRzxsVOMapper wjdrRzxsVOMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询导入数据日志
	 * @param map
	 * @return
	 */
	public List<WjdrRzxsVO> wjdrRzxsVOForPageByWjlxSql(Map<String, Object> map) {
		return wjdrRzxsVOMapper.getWjdrRzxsVOForPage(map);
	}
	
	/**
	 * 导入日志查询
	 * 查询总条数*/
	public int getTotalCount(Map<String, Object> map) {
		return wjdrRzxsVOMapper.getTotalCount(map);
	}
	
	

	public List<WjdrRzxsVO> sjdTyhjcxForPage(Map<String, Object> map) {
		return wjdrRzxsVOMapper.sjdTyhjcxForPage(map);
	}

	

	/**
	 * @param map
	 *            bdpzxh 类型编码 czrydm 操作人员代码 startDate 开始时间 endDate 结束时间
	 */
	public CallableStatementObj comparisonData(final String bdpzxh, final String czrydmm, final String startDate, final String endDate) {
		if (StringUtils.isNotBlank(bdpzxh)) {
			final List<String> list = wjdrRzxsVOMapper.getBdckgcmc(bdpzxh);
			return jdbcTemplate.execute(new CallableStatementCreator() {
				public CallableStatement createCallableStatement(Connection con) throws SQLException {

					String storedProc = "{call " + list.get(0) + "(?,?,?,?,?,?)}";
					log.debug(storedProc);
					CallableStatement cs = con.prepareCall(storedProc);
					cs.setString(1, bdpzxh);// 设置输入参数的值
					cs.setString(2, startDate);// 设置输入参数的值
					cs.setString(3, endDate);// 设置输入参数的值
					cs.setString(4, czrydmm);// 设置输入参数的值
					cs.registerOutParameter(5, OracleTypes.VARCHAR);// 注册输出参数的类型
					cs.registerOutParameter(6, OracleTypes.VARCHAR);// 注册输出参数的类型
					return cs;
				}
			}, new CallableStatementCallback<CallableStatementObj>() {
				public CallableStatementObj doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
					cs.execute();
					return new CallableStatementObj(cs.getString(5),cs.getString(6));
				}
			});
		}
		return null;
	}
	
	
	
	/**
	 * 本月无数据
	 * @param wjdrRzxsVO
	 */
	public void addWjdrRzxsVO(WjdrRzxsVO wjdrRzxsVO){
		wjdrRzxsVOMapper.addWjdrRzxsVO(wjdrRzxsVO);
	}
	
	
	/**
	 * 废弃该数据
	 * @param wjdrRzxsVO
	 */
	public void delWjdrRzxsVOLog(WjdrRzxsVO wjdrRzxsVO){
		wjdrRzxsVOMapper.delWjdrRzxsVOLog(wjdrRzxsVO);
	}
	
	
//	List<WjdrRzxsVO> sjgzbdSjgzbdcxYbdForPage(Map<String, Object> map){
//	    
//	}
//
//    int sjgzbdSjgzbdcxYbdTotalCount(Map<String, Object> map);
}
