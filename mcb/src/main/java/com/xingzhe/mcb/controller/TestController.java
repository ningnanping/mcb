package com.xingzhe.mcb.controller;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

	@ResponseBody
	@RequestMapping(value="/index.html")
	public String   test1(){
		return "<input type='text' id='123'>";
	}
}
