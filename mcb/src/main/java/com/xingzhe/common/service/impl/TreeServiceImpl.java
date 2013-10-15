package com.xingzhe.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.common.dao.TreeDao;
import com.xingzhe.common.dao.redis.TreeRedisDao;
import com.xingzhe.common.domain.Tree;
import com.xingzhe.common.service.TreeService;

@Service("treeService")
public class TreeServiceImpl  implements TreeService  {

	@Autowired
	private TreeDao<Tree>  treeDao;

	@Autowired
	private TreeRedisDao treeRedisDao;

	@Override
	public List<Tree> getTreeObjectByParentId(int parentId, String treeName) {
		List<Tree> list = treeRedisDao.getListTree(parentId, treeName);
		if (list == null || list.size() <= 0) {
			list = treeDao.getTreeObjectByParentId(parentId);
			if (list != null && list.size() > 0) {
				treeRedisDao.saveListTree(parentId, treeName, list);
			}
		}
		return list;
	}

	@Override
	public List<Tree> getTreeObjectByTreeName(String treeName) {
		List<Tree> list = treeRedisDao.getListTree(-1, treeName);
		if (list == null || list.size() <= 0) {
			list=treeDao.getTreeObjectByTreeName(treeName);
			if (list != null && list.size() > 0) {
				treeRedisDao.saveListTree(-1, treeName, list);
			}
		}
		return list;
	}
}
