package com.xingzhe.common.service;

import java.util.List;

import com.xingzhe.common.domain.Tree;

public interface TreeService  {

	List<Tree> getTreeObjectByTreeName(String treeName);

	List<Tree> getTreeObjectByParentId(int parentId, String treeName);
}
