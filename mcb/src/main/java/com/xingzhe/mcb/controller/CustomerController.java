package com.xingzhe.mcb.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.xingzhe.mcb.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(ProductController.class);

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/index.html")
	public String index() {
		return "resourse/jsp/mcb/customer";
	}

	@ResponseBody
	@RequestMapping(value = "/save.html", produces = TEXT_HTML_PRODUCES)
	public String saveCustomer(HttpServletRequest request) {
		String name = request.getParameter("name");
		int sex = Integer.parseInt(request.getParameter("sex"));
		int customerLevelId = Integer.parseInt(request
				.getParameter("customerLevelId"));
		int agentId = Integer.parseInt(request.getParameter("agentId"));
		int handId = Integer.parseInt(request.getParameter("handId"));
		int babyMonth = Integer.parseInt(request.getParameter("babyMonth"));
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		Customer o = new Customer();
		o.setAgentId(agentId);
		o.setName(name);
		o.setSex(sex);
		o.setCustomerLevelId(customerLevelId);
		o.setHandId(handId);
		o.setBabyMonth(babyMonth);
		o.setEmail(email);
		o.setPhoneNumber(phoneNumber);
		o.setVipId(getVipId());
		o.setCreateTime(new Date());
		log.debug(o.toString());
		try {
			customerService.saveCustomer(o);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(new ActionStatus(1001, "保存失败"));
		}
		return JSON.toJSONString(new ActionStatus(1000, "保存成功"));
	}

	@ResponseBody
	@RequestMapping(value = "/update.html", produces = TEXT_HTML_PRODUCES)
	public String update(Customer o) {
		log.debug(o.toString());
		try {
			customerService.updateCustomer(o);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(new ActionStatus(1001, "保存失败"));
		}

		return JSON.toJSONString(new ActionStatus(1000, "保存成功"));
	}

	@ResponseBody
	@RequestMapping(value = "/del.html", produces = TEXT_HTML_PRODUCES)
	public String del(
			@RequestParam(value = "id", required = false, defaultValue = "-1") int id) {
		if (id != -1) {
			try {
				customerService.delCustomer(id);
			} catch (Exception e) {
				e.printStackTrace();
				return JSON.toJSONString(new ActionStatus(1001, "保存失败"));
			}

			return JSON.toJSONString(new ActionStatus(1000, "保存成功"));
		} else {
			return JSON.toJSONString(new ActionStatus(1002, "ID不合法操作失误"));
		}
	}

	@ResponseBody
	@RequestMapping(value = "/list.json", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getSelectOrder(
			@RequestParam(defaultValue = "1", required = true, value = "page") int page,
			@RequestParam(defaultValue = "10", required = true, value = "rows") int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int total = customerService.selectCustomerListForCount(map);

		// 分页的参数
		// start 代表从那个开始取
		// end 代表取多少个
		map.put("start", mysqlGetStart(page, rows));
		map.put("end", rows);
		List<Customer> list = customerService.selectCustomerListForPage(map);

		return new DataGrid(total, list);
	}

	private String getVipId() {
		String s = "000000000";
		s += customerService.getVipId();
		s = s.substring(s.length() - 9, s.length());
		s = "C" + s;
		return s;
	}
}
