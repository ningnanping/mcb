package com.xingzhe.zhzs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.framework.mapper.SqlMapper;


public interface FileTypeMapper extends SqlMapper {

	/**
	 * 根据 部门的Id 获取文件类型
	 * @param deptId
	 * @return
	 */
	@Select("select WJLX_DM,WJLX_MC from T_XT_WJDR_WJLX   where XY_BJ='1' and CJBM_DM =#{deptId}order by WJLX_DM")
	@Results(value = {
			@Result(column="WJLX_DM",property="id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
			@Result(column="WJLX_MC",property="name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
	})
	List<SelectBoxObj> getFileTypeByDepartmentId(@Param(value = "deptId") String deptId);
}
