package com.xingzhe.framework.mapper;

import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @Title ：
 * @Description ：通过扫描的模式，扫描目录在com/cctc/ /mapper目录下，所有的mapper都继承SqlMapper接口的接口，
 *              这样一个bean就可以了
 * @author ：LuFengLiang
 * @Copyright: Copyright (c) @2012-8-10
 * @version ： 1.0
 */

@CacheNamespace(size = 1024)
public interface SqlMapper {

}
