package com.xingzhe.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.common.dao.TreeDao;
import com.xingzhe.common.domain.Tree;
import com.xingzhe.common.service.TreeService;
import com.xingzhe.framework.cache.redis.NeedRedisCached;

@Service("treeService")
public class TreeServiceImpl implements TreeService {

	@Autowired
	private TreeDao<Tree> treeDao;

	@Override
	@NeedRedisCached(type="HASH",returnType=Tree.class,preKey="COM.XINGZHE.COMMON.DOMAIN.TREE.ID",isArray=true,keyArgs={1,2})
	public List<Tree> getTreeObjectByParentId(int parentId, String treeName) {
		return treeDao.getTreeObjectByParentId(parentId);
	}

	@Override
	@NeedRedisCached(type="HASH",returnType=Tree.class,preKey="COM.XINGZHE.COMMON.DOMAIN.TRE.TREENAME",isArray=true,keyArgs={1})
	public List<Tree> getTreeObjectByTreeName(String treeName) {
		return treeDao.getTreeObjectByTreeName(treeName);
	}
}
