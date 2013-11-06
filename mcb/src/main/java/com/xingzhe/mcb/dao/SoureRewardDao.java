package com.xingzhe.mcb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.mcb.domain.SoureReward;
import com.xingzhe.mcb.mapper.SoureRewardMapper;

@Repository
public class SoureRewardDao {
	@Autowired
	private  SoureRewardMapper soureRewardMapper;
	public List<SoureReward> selectListSoureRewardForForPage(Map<String,Object> map){
		return soureRewardMapper.selectListSoureRewardForForPage(map);
	}
	public  int selectListSoureRewardForForTotal(Map<String,Object> map){
		return soureRewardMapper.selectListSoureRewardForForTotal(map);
	}
	
	public void  saveSoureReward(SoureReward soureReward){
		soureRewardMapper.saveSoureReward(soureReward);
	}
}
