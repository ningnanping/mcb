package com.xingzhe.common.dao.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.xingzhe.common.domain.Tree;
import com.xingzhe.framework.cache.redis.RedisCache;

@Repository
public class TreeRedisDao {

	private static final Logger log = LoggerFactory.getLogger(TreeRedisDao.class);
	@Autowired
	private RedisCache redisCache;

	static final String TREE_LIST_PREFIX = "CCTC.XINGZHE.COMMON.DOMAIN.TREE.";

	public boolean saveListTree(final int parentId, final String treeName, final List<Tree> list) {
		try {
			redisCache.putMap(TREE_LIST_PREFIX+treeName, ""+parentId, JSON.toJSONString(list));
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public List<Tree> getListTree(final int parentId, final String treeName) {
		try {
			String s=redisCache.getMap(TREE_LIST_PREFIX+treeName, ""+parentId);
			return JSON.parseArray(s, Tree.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
