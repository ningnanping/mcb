package com.xingzhe.mcb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingzhe.mcb.service.EmployeeTypeService;

@Controller
@RequestMapping(value = "/common")
public class CommonController {
	@Autowired
	private EmployeeTypeService employeeTypeService;

	
	@ResponseBody
	@RequestMapping(value = "/employeeType.json")
	public Object getAllEmployeeType() {
		return employeeTypeService.getAllEmployeeType();
	}

}
