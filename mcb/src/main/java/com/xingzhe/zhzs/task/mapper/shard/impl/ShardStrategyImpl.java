package com.xingzhe.zhzs.task.mapper.shard.impl;

import com.xingzhe.zhzs.task.domain.AppTest;
import com.xingzhe.zhzs.task.mapper.shard.AppTestShardStrategy;



public class ShardStrategyImpl implements AppTestShardStrategy {
	@Override
	public String getTargetTableName(String baseTableName, Object params,String mapperId) {
		AppTest at=(AppTest)params;
		if(at.getSid()==1){
			return baseTableName+"_1";
		}else{
			return baseTableName+"_2";
		}
	}

}
