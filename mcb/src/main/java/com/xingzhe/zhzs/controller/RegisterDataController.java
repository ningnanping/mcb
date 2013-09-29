package com.xingzhe.zhzs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingzhe.framework.controller.BaseController;
import com.xingzhe.framework.domain.CallableStatementObj;
import com.xingzhe.framework.domain.DataGrid;
import com.xingzhe.framework.domain.ResultObj;
import com.xingzhe.zhzs.domain.WjdrRzxsVO;
import com.xingzhe.zhzs.service.RegisterDataService;


/**
 * 登记数据比对
 * @author LuTang
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterDataController extends BaseController {
	
	@Autowired
	private RegisterDataService registerDataService;
	
	@ResponseBody
	@RequestMapping("/list.json")
	public Object getRegisterDataForLog(@RequestParam(defaultValue = "", required = true, value = "wjlx") String wjlx,
	        @RequestParam(defaultValue = "", required = true, value = "sfjg") String sfjg,
	        @RequestParam(defaultValue = "1900-01-01", required = true, value = "startDate") String startDate,
	        @RequestParam(defaultValue = "9999-12-31", required = true, value = "endDate") String endDate,
            @RequestParam(defaultValue = "1", required = true, value = "page") int page,
            @RequestParam(defaultValue = "10", required = true, value = "rows") int rows){
	    Map<String,Object> map=new HashMap<String,Object>();
	    map.put("startDate", getStartTimeHH24MISS(startDate));
	    map.put("endDate", getEndTimeHH24MISS(endDate));
	    map.put("sfjg",sfjg);
	    map.put("sjfw","");
	    map.put("wjlx", wjlx);
	    
	    int total=registerDataService.sjdTyhjcxTotalCount(map);
	    map.put("start", getStart(page, rows));
        map.put("end", getEnd(total, page, rows));
	    List<WjdrRzxsVO> list=registerDataService.sjdTyhjcxForPage(map);
	
		return new DataGrid(total,list);
	}
	
	
	/**
	 * 统一户籍
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/unifiedRegistration.json")
	public  Object unifiedRegistration(
	        @RequestParam(defaultValue = "", required = true, value = "wjlx") String wjlx,
	        @RequestParam(defaultValue = "", required = true, value = "bdwjlx") String bdwjlx,
            @RequestParam(defaultValue = "", required = true, value = "xh") String pcxh){
	    String  czrydm="jpadmin";
	    String swjdm="12410000000";
	    CallableStatementObj s=registerDataService.unifiedRegistration(wjlx,bdwjlx, pcxh, czrydm, swjdm);
	    System.out.println(s);
	    return new ResultObj(1000);
	}

}
