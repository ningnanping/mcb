package com.xingzhe.zhzs.task.mapper;

import com.xingzhe.framework.mapper.SqlMapper;
import com.xingzhe.zhzs.task.domain.AppTest;

public interface AppTestMapper extends SqlMapper{
	
	void add(AppTest appTest);

}
