package com.xingzhe.zhzs.task.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xingzhe.framework.mapper.SqlMapper;
import com.xingzhe.zhzs.task.domain.UserDept;

public interface UserDeptMapper extends SqlMapper {

	@Results(value={
			@Result(column="rydm",property="rydm",javaType=String.class,jdbcType=JdbcType.VARCHAR),
			@Result(column="rymc",property="rymc",javaType=String.class,jdbcType=JdbcType.VARCHAR),
			@Result(column="jgdm",property="jgdm",javaType=String.class,jdbcType=JdbcType.VARCHAR),
			@Result(column="jgmc",property="jgmc",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	
	@Select("select t1.swry_dm as rydm,t1.mc as rymc ,t2.swjg_dm as jgdm,t2.mc as jgmc,t2.sjswjg_dm as sjswjgdm  from T_DM_GY_SWRY t1 left join T_DM_GY_SWJG t2 on  t1.swjg_dm=t2.swjg_dm where  t1.swry_dm ='${id}'")
	UserDept getUserDeptById(@Param(value = "id") String id);
	
	@Select("select t1.swry_dm as rydm,t1.mc as rymc from T_DM_GY_SWRY t1 where  t1.swjg_dm ='${id}'")
	List<UserDept> getUsersByDeptId(@Param(value = "id") String id);
	
	@Select("select t2.swjg_dm as jgdm,t2.mc as jgmc,t2.sjswjg_dm as sjswjgdm  from T_DM_GY_SWJG t2 where t2.sjswjg_dm ='${id}'")
	List<UserDept> getDeptsBySjDeptId(@Param(value = "id") String id);
}
