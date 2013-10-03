package com.xingzhe.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingzhe.framework.cache.UserLoginCache;
import com.xingzhe.framework.util.CookieUtil;
import com.xingzhe.framework.util.UuidUtil;

@Controller
@RequestMapping(value="/common")
public class LoginController {
	
	@Autowired
	private UserLoginCache userLoginCache;
	
	/**
	 * 登录
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login.html")
	public String login(HttpServletResponse response,HttpServletRequest  request){
		//获取所需要的参数
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String plantFrom=request.getParameter("plantFrom");
		String isSystem=request.getParameter("isSystem");
		//对参数的验证
		if(StringUtils.isBlank(plantFrom)){
			plantFrom="WEB_FROM";
		}
		if(StringUtils.isBlank(isSystem)||!isSystem.equalsIgnoreCase("IS_SYSTEM")){
			isSystem="NO_SYSTEM";
		}
		//对用户名和密码的验证  验证通过加入到cookie中
		if("admin".equalsIgnoreCase(userName)&&"admin".equals(password)){
			CookieUtil.getInstance().addCookie(response, "userName", userName);
			CookieUtil.getInstance().addCookie(response, "platFrom", plantFrom);
			CookieUtil.getInstance().addCookie(response, "isSystem", isSystem);
			String  uuid=UuidUtil.getUUid();
			CookieUtil.getInstance().addCookie(response, "uuid",uuid);
			String acessToken =userLoginCache.putAcessToken(userName, plantFrom, uuid);
			CookieUtil.getInstance().addCookie(response, "acessToken", acessToken);
			return "OK";
		}
		return "ERROR";
	}

	@ResponseBody
	@RequestMapping(value="/test.html")
	public String login(){
		return "OK";
	}
}
