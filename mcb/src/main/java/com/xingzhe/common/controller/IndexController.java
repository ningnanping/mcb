package com.xingzhe.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingzhe.common.dao.redis.UserLoginCache;
import com.xingzhe.framework.util.CookieUtil;

@Controller
public class IndexController {

	@Autowired
	private UserLoginCache  userLoginCache;
	
	@RequestMapping("/index.html")
	public Object index(HttpServletRequest request, HttpServletResponse response) {
		String acessToken=CookieUtil.getInstance().getCookieValueByName(request, "acessToken");
		String platFrom=CookieUtil.getInstance().getCookieValueByName(request, "platFrom");
		String userName=CookieUtil.getInstance().getCookieValueByName(request, "userName");
		String uuid=CookieUtil.getInstance().getCookieValueByName(request, "uuid");
		String acessTokenfrom=null;
		if(userName==null||platFrom==null||uuid==null){
			return  "resourse/jsp/common/login";  
		}
		acessTokenfrom =userLoginCache.getAcessToken(userName, platFrom, uuid);
		if(StringUtils.isBlank(acessToken)||!acessToken.equals(acessTokenfrom)){
			return  "resourse/jsp/common/login";   
		}
		return new ModelAndView("resourse/jsp/common/main","userName",userName);
	}

}
