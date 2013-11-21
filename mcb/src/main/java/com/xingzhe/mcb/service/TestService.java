package com.xingzhe.mcb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xingzhe.framework.cache.redis.NeedRedisCached;

@Service("testService")
public class TestService {

	@NeedRedisCached(type="HASH",returnType=String.class,endKey="COM.XINGZHE.TEST",isArray=true)
	public Object getOne() {
	    List<String> list=new ArrayList<>();
	    list.add(UUID.randomUUID().toString());
	    list.add(UUID.randomUUID().toString());
		return list;
	}

}
