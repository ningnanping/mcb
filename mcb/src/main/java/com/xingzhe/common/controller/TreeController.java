package com.xingzhe.common.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingzhe.common.service.TreeService;

/**
 * @Title :
 * @Description :功能树的controller
 * @author :LuFengLiang
 * @Copyright: Copyright (c) @2012-8-10
 * @version : 1.0
 */
@Controller
@RequestMapping("/tree")
public class TreeController
{
    
    @Autowired
    private TreeService treeService;
    
    @ResponseBody
    @RequestMapping(value = "/{treeName}/index.json")
    public Callable<Object> getTreeData(@PathVariable final String treeName)
    {
        return new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                return treeService.getTreeObjectByTreeName(treeName);
            }
        };
        
    }
    
    @ResponseBody
    @RequestMapping(value = "/{treeName}/{parentId}/child.json")
    public Object getTreeData(@PathVariable final int parentId, @PathVariable final String treeName)
    {
        return new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                return treeService.getTreeObjectByParentId(parentId, treeName);
            }
        };
    }
}
