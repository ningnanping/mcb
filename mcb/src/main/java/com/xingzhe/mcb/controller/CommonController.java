package com.xingzhe.mcb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingzhe.framework.controller.BaseController;
import com.xingzhe.mcb.service.CustomerLevelService;
import com.xingzhe.mcb.service.EmployeeService;
import com.xingzhe.mcb.service.EmployeeTypeService;

@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController
{
    @Autowired
    private EmployeeTypeService employeeTypeService;
    
    @Autowired
    private CustomerLevelService customerLevelService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @ResponseBody
    @RequestMapping(value = "/employeeType.json")
    public Object getAllEmployeeType()
    {
        return employeeTypeService.getAllEmployeeType();
    }
    
    @ResponseBody
    @RequestMapping(value = "/customerLevel.json")
    public Object getAllCustomerLevel()
    {
        return customerLevelService.getAllCustomerLevel();
    }
    
    @ResponseBody
    @RequestMapping(value = "/employee.json")
    public Object getAllEmployee()
    {
        return employeeService.getAllEmployee();
    }
    
}
