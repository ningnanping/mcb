package com.xingzhe.zhzs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.framework.mapper.SqlMapper;

public interface RevenueTypeMapper extends SqlMapper {
	
	
	/**
	 * 根据sql 查询出所有的税种
	 * @return
	 */
	@Select("select SZ_MC,SZ_DM  from (select distinct SZ_MC , SZ_DM,SZ_BJ from t_zs_sz_by) where SZ_BJ='1' order by SZ_DM")
	@Results(value = {
			@Result(column="SZ_DM",property="id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
			@Result(column="SZ_MC",property="name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
	})
	List<SelectBoxObj> getAllRevenueType();
	
}
