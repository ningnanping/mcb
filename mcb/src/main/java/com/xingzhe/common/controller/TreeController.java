package com.xingzhe.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingzhe.common.service.TreeService;

/**
 * @Title ：
 * @Description ：Spring Mvc的 测试类
 * @author ：LuFengLiang
 * @Copyright: Copyright (c) @2012-8-10
 * @version ： 1.0
 */
@Controller
@RequestMapping("/tree")
public class TreeController {

	@Autowired
	private TreeService treeService;
	
	@ResponseBody
	@RequestMapping(value = "/{treeName}/index.json")
	public Object getTreeData(@PathVariable String treeName) {
		return treeService.getTreeObjectByTreeName(treeName);
	}

	@ResponseBody
	@RequestMapping(value = "/{treeName}/{parentId}/child.json")
	public Object getTreeData(@PathVariable int parentId, @PathVariable String treeName) {
		return treeService.getTreeObjectByParentId(parentId, treeName);
	}
}
