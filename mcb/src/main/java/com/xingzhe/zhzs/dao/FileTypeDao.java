package com.xingzhe.zhzs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.zhzs.mapper.FileTypeMapper;

@Repository
public class FileTypeDao {

	@Autowired
	private FileTypeMapper fileTypeMapper;

	public List<SelectBoxObj> getFileTypeByDepartmentId(String deptId) {
		return fileTypeMapper.getFileTypeByDepartmentId(deptId);
	}
}
