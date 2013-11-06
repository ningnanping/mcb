package com.xingzhe.mcb.mapper;

import java.util.List;
import java.util.Map;

import com.xingzhe.framework.mapper.SqlMapper;
import com.xingzhe.mcb.domain.SoureReward;

public interface SoureRewardMapper extends SqlMapper {
	
	List<SoureReward> selectListSoureRewardForForPage(Map<String,Object> map);
	int selectListSoureRewardForForTotal(Map<String,Object> map);
	
	void  saveSoureReward(SoureReward soureReward);

}
