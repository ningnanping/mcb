package com.xinzhe.system.execle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xingzhe.framework.mapper.SqlMapper;
import com.xingzhe.system.execle.domain.Excel;

public interface ExcelMapper extends SqlMapper {

	@Results(value = { @Result(column = "WJLX_DM", property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(column = "TABLE_NAME", property = "tableName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(column = "COL_NAME", property = "colName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(column = "SXH", property = "sxh", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(column = "MRZ", property = "mrz", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(column = "DATA_SOUR", property = "dataSour", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(column = "COL_TYPE", property = "colType", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(column = "OUTCOL", property = "outCol", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(column = "FORMAT", property = "dateFormat", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("select WJLX_DM,TABLE_NAME,OUTCOL,SXH,COL_NAME,MRZ,DATA_SOUR,COL_TYPE,FORMAT from T_XT_WJDR_EXCEL t where t.wjlx_dm=#{wjlx} order by data_sour")
	public List<Excel> getWjdrExcelList(@Param(value = "wjlx") String wjlx);
}
