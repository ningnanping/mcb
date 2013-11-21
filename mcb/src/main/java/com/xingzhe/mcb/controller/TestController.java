package com.xingzhe.mcb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingzhe.mcb.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService  testService;
	
	@ResponseBody
	@RequestMapping("/index.json")
	public Object get(){
		return testService.getOne();
	}
}
