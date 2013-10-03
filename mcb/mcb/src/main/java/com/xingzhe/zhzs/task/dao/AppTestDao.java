package com.xingzhe.zhzs.task.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.zhzs.task.domain.AppTest;
import com.xingzhe.zhzs.task.mapper.AppTestMapper;

@Repository
public class AppTestDao {

	@Autowired
	private AppTestMapper appTestMapper;
	
	public void add(AppTest appTest){
		appTestMapper.add(appTest);
	}
	
}
