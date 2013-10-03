package com.xingzhe.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xingzhe.framework.cache.UserLoginCache;
import com.xingzhe.framework.util.CookieUtil;

/**
 * 登录的拦截器
 * @author LuTang
 *
 */
public class LoginInterceptor implements HandlerInterceptor  {

	private static final  Logger log = LoggerFactory.getLogger(LoginInterceptor.class);  
	
	@Autowired
	private UserLoginCache  userLoginCache;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.debug(request.getRequestURL().toString());  
		log.debug("==============执行顺序: 1、preHandle================");  
		
		//在Cookie获取值
		String acessToken=CookieUtil.getInstance().getCookieValueByName(request, "acessToken");
		String platFrom=CookieUtil.getInstance().getCookieValueByName(request, "platFrom");
		String userName=CookieUtil.getInstance().getCookieValueByName(request, "userName");
		String uuid=CookieUtil.getInstance().getCookieValueByName(request, "uuid");
		//在缓存中获取acessToken并进行比较
		String acessTokenfrom=null;
		if(userName==null||platFrom==null||uuid==null){
			request.getRequestDispatcher("/login.html").forward(request, response);  
			return false;  
		}
		acessTokenfrom =userLoginCache.getAcessToken(userName, platFrom, uuid);
		
		if(StringUtils.isBlank(acessToken)||!acessToken.equals(acessTokenfrom)){
			request.getRequestDispatcher("/login.html").forward(request, response);  
			return false;   
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug("==============执行顺序: 2、postHandle================");  
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("==============执行顺序: 3、afterCompletion================");  
	}

}
