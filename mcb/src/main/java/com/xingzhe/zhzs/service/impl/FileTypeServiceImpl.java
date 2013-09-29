package com.xingzhe.zhzs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.zhzs.dao.FileTypeDao;
import com.xingzhe.zhzs.dao.redis.FileTypeRedisDao;
import com.xingzhe.zhzs.service.FileTypeService;

@Service("fileTypeService")
public class FileTypeServiceImpl implements FileTypeService {

	@Autowired
	private FileTypeDao fileTypeDao;
	
	@Autowired
	private  FileTypeRedisDao  fileTypeRedisDao;

	public List<SelectBoxObj> getFileTypeBydeptid(String deptId) {
		List<SelectBoxObj> list=fileTypeRedisDao.getFileTypeByDeptId(deptId);
		if(list==null||list.size()==0){
			list=fileTypeDao.getFileTypeByDepartmentId(deptId);
			if(list!=null&&list.size()!=0){
				fileTypeRedisDao.saveFileTypeByDeptId(list, deptId);
			}
		}
		return list;
	}

}
