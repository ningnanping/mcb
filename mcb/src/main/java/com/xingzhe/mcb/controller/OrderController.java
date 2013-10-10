package com.xingzhe.mcb.controller;

import com.xingzhe.framework.controller.BaseController;
import com.xingzhe.mcb.dao.OrderDao;
import com.xingzhe.mcb.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderDao<Order> orderDao;

	@RequestMapping("/test1.html")
	public void test1() {
		for (int i = 0; i < 2; i++) {
			orderDao.addorder(1, "1#2;2#2");
		}
	}

	@RequestMapping("/test2.html")
	public void test2() {
		for (int i = 0; i < 3; i++) {
			orderDao.addorder(1, "1#2;2#2");
		}
	}
}
