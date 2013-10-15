package com.xingzhe.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xingzhe.common.domain.Tree;
import com.xingzhe.framework.mapper.SqlMapper;

public interface TreeMapper  extends SqlMapper {

	
	@Results(value={
			@Result(id = true, property = "id", column = "ID", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "iconCls", column = "ICON_CLS", javaType = String.class, jdbcType = JdbcType.VARCHAR), 
			@Result(property = "state", column = "STATE", javaType = String.class, jdbcType = JdbcType.VARCHAR), 
			@Result(property = "text", column = "TEXT", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "extend", column = "EXTEND", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
	@Select("SELECT ID, ICON_CLS, STATE,TEXT,EXTEND FROM CCTC.CCTCT_TREES WHERE PARENT_ID=#{parentId} AND IS_DEL=0  ")
	List<Tree> getTreeObjectByParentId(int parentId);

	@Results(value={
			@Result(id = true, property = "id", column = "ID", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "iconCls", column = "ICON_CLS", javaType = String.class, jdbcType = JdbcType.VARCHAR), 
			@Result(property = "state", column = "STATE", javaType = String.class, jdbcType = JdbcType.VARCHAR), 
			@Result(property = "text", column = "TEXT", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "extend", column = "EXTEND", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
	@Select("SELECT ID, ICON_CLS, STATE, TEXT,EXTEND FROM CCTC.CCTCT_TREES WHERE TREE_NAME=#{treeName} AND IS_DEL=0")
//	@Options(useCache = true, flushCache = false, timeout = 10000)
	List<Tree> getTreeObjectByTreeName(String treeName);
}
