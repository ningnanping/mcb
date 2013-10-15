package com.xingzhe.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.common.domain.Tree;
import com.xingzhe.common.mapper.TreeMapper;
import com.xingzhe.framework.dao.BaseDao;

@Repository
public class TreeDao<T extends Tree> extends BaseDao<T> {

	@Autowired
	private TreeMapper  treeMapper;

	public List<Tree> getTreeObjectByParentId(int parentId) {
		return treeMapper.getTreeObjectByParentId(parentId);
	}

	public List<Tree> getTreeObjectByTreeName(String treeName) {
		List<Tree> list=treeMapper.getTreeObjectByTreeName(treeName);
		if(list!=null&&list.size()>0){
			list.get(0).setListTree(treeMapper.getTreeObjectByParentId(list.get(0).getId()));
		}
		return list;
	}

}
