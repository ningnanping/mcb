package com.xingzhe.mcb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.controller.BaseController;
import com.xingzhe.framework.domain.ActionStatus;
import com.xingzhe.framework.domain.DataGrid;
import com.xingzhe.mcb.domain.Customer;
import com.xingzhe.mcb.mapper.CustomerMapper;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {
	
	private static final Logger log=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@RequestMapping("/index.html")
	public String index() {
		return "resourse/jsp/mcb/customer";
	}
	
	@RequestMapping(value = "/save.json", produces = TEXT_HTML_PRODUCES)
	public String save(Customer o) {
		log.debug(o.toString());
		return JSON.toJSONString(new ActionStatus(1000,"我去问问1"));
	}

	@ResponseBody
	@RequestMapping(value = "/update.html",produces=TEXT_HTML_PRODUCES)
	public String update(Customer o) {
		log.debug(o.toString());
		return JSON.toJSONString(new ActionStatus(1000,"我去问问"));
	}

	@ResponseBody
	@RequestMapping(value = "/del.html", produces = TEXT_HTML_PRODUCES)
	public String del(@RequestParam(value="id",required=false,defaultValue="-1")int id) {
		if(id!=-1){
			//identityInfoService.delIdentityInfo(id);
			return JSON.toJSONString(new ActionStatus(1000,"OK"));
		}else{
			return JSON.toJSONString(new ActionStatus(1001,"ERROR"));
		}
	}
	@ResponseBody
	@RequestMapping(value="/list.json",produces= MediaType.APPLICATION_JSON_VALUE)
	public Object getSelectOrder(@RequestParam(defaultValue = "1", required = true, value = "page") int page,
								 @RequestParam(defaultValue = "10", required = true, value = "rows") int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int total = customerMapper.selectCustomerListForCount(map);

		//分页的参数
		// start 代表从那个开始取
		//end 代表取多少个
		map.put("start", mysqlGetStart(page,rows));
		map.put("end", rows);
		List<Customer> list = customerMapper.selectCustomerListForPage(map);

		return new DataGrid(total, list);
	}
}
