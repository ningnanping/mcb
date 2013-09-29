package com.xingzhe.zhzs.service;

import java.util.List;

import com.xingzhe.framework.domain.SelectBoxObj;

public interface FileTypeService {
	public List<SelectBoxObj>  getFileTypeBydeptid(String deptId);
}
