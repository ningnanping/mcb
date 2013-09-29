package com.xingzhe.system.execle.dao;


import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.xingzhe.system.execle.domain.Excel;
import com.xinzhe.system.execle.mapper.ExcelMapper;


@Repository
public class ExcelDao {

	@Autowired
	private ExcelMapper excelMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 根据文件名称获取excel的动态列名和表名
	 * @param wjlx
	 * @return
	 */
	public List<Excel> getExcelList(String wjlx) {
		return excelMapper.getWjdrExcelList(wjlx);
	}
	
	
	public SqlRowSet  getSqlRowSetForExecl(String sql,Object[] args,int[] typeArgs){
		return jdbcTemplate.queryForRowSet(sql, args,typeArgs);
	}
}
