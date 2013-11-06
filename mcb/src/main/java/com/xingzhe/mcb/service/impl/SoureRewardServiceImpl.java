package com.xingzhe.mcb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.mcb.dao.SoureRewardDao;
import com.xingzhe.mcb.domain.SoureReward;
import com.xingzhe.mcb.service.SoureRewardService;

@Service("soureRewardService")
public class SoureRewardServiceImpl implements SoureRewardService {

	@Autowired
	private SoureRewardDao SoureRewardDao;

	@Override
	public List<SoureReward> selectListSoureRewardForForPage(
			Map<String, Object> map) {
		return SoureRewardDao.selectListSoureRewardForForPage(map);
	}

	@Override
	public int selectListSoureRewardForForTotal(Map<String, Object> map) {
		return SoureRewardDao.selectListSoureRewardForForTotal(map);
	}

	@Override
	public void saveSoureReward(SoureReward soureReward) {
		SoureRewardDao.saveSoureReward(soureReward);
	}

}
