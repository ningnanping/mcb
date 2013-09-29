package com.xingzhe.zhzs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.framework.mapper.SqlMapper;

public interface DepartmentMapper extends SqlMapper{

	/**
	 * 查询出所有的部门
	 * @return
	 */
	@Select("select BM_DM ,MC from  T_ZS_DM_WBMNB where BJ='1' order by BM_DM")
	@Results(value = {
			@Result(column="BM_DM",property="id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
			@Result(column="mc",property="name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
	})
	List<SelectBoxObj> getAllDepartment();
	
	/**
	 * 根据税种类型Id 查询 部门
	 * @return
	 */
	@Select("select BM_DM,MC from T_ZS_DM_WBMNB ,(select distinct SSBM,SZ_DM from t_zs_sz_by) where BJ='1' AND BM_DM = SSBM  and sz_dm =#{revenueTypeId} order by BM_DM")
	@Results(value = {
			@Result(column="BM_DM",property="id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
			@Result(column="MC",property="name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
	})
	List<SelectBoxObj> getDepartmentByRevenueTypeId(String revenueTypeId);
}
