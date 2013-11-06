package com.xingzhe.mcb.service;

import java.util.List;
import java.util.Map;

import com.xingzhe.mcb.domain.SoureReward;

public interface SoureRewardService {
	List<SoureReward> selectListSoureRewardForForPage(Map<String, Object> map);

	int selectListSoureRewardForForTotal(Map<String, Object> map);

	void saveSoureReward(SoureReward soureReward);
}
