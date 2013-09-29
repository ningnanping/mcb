package com.xingzhe.zhzs.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.controller.BaseController;
import com.xingzhe.zhzs.domain.UserModel;

/**
 * 登录控制器
 * 
 * @author lys
 */
@Controller
public class LoginController extends BaseController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login" ,method = RequestMethod.POST)
	public String login(@ModelAttribute("user") UserModel user,
			HttpServletRequest httpServletRequest) {
		
		if(logger.isDebugEnabled()){
			logger.debug("User Login Event:" + JSON.toJSONString(user));
		}
		
		
		return "main";
	}

}