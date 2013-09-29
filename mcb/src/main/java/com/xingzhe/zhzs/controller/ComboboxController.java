package com.xingzhe.zhzs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingzhe.zhzs.service.DepartmentService;
import com.xingzhe.zhzs.service.FileTypeService;
import com.xingzhe.zhzs.service.RevenueTypeService;
import com.xingzhe.zhzs.service.TableStyleTypeService;

@Controller
@RequestMapping("/common")
public class ComboboxController
{
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private FileTypeService fileTypeService;
    
    @Autowired
    private RevenueTypeService revenueTypeService;
    
    @Autowired
    private TableStyleTypeService tableStyleTypeService;
    
    /**
     * 获取所有的组织机构
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dept.json")
    public Object getAllDept()
    {
        return departmentService.getAllDepartment();
    }
    
    /**
     * 根据部门获取 文件类型
     * 
     * @param deptId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dept/{deptId}/filetype.json")
    public Object getFileTypeByDeptId(@PathVariable(value = "deptId") String deptId)
    {
        return fileTypeService.getFileTypeBydeptid(deptId);
    }
    
    /**
     * 获取所有的税种
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tax.json")
    public Object getAllTaxType()
    {
        return revenueTypeService.getAllRevenueType();
    }
    
    /**
     * 
     * @param taxId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tax/{taxId}/tableType.json")
    public Object getTableTypeByTaxId(@PathVariable(value = "taxId") String taxId)
    {
        return tableStyleTypeService.getRevenueSampleByRevenueType(taxId);
    }
    
    
    /**
     * 根据随缘种类获取部门Id
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tax/{taxId}/dept.json")
    public Object getDepartmentByRevenueTypeId(@PathVariable(value = "taxId") String taxId){
        return departmentService.getDepartmentByRevenueTypeId(taxId);
    }
    
}
