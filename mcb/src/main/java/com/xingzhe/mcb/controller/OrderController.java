package com.xingzhe.mcb.controller;

import com.xingzhe.framework.controller.BaseController;
import com.xingzhe.framework.domain.DataGrid;
import com.xingzhe.mcb.dao.OrderDao;
import com.xingzhe.mcb.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@ResponseBody
	@RequestMapping(value="/list.json",produces= MediaType.APPLICATION_JSON_VALUE)
	public Object getSelectOrder(@RequestParam(defaultValue = "1", required = true, value = "page") int page,
								 @RequestParam(defaultValue = "10", required = true, value = "rows") int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int total = orderDao.selectOrderCount(map);

		//分页的参数
		// start 代表从那个开始取
		//end 代表取多少个
		map.put("start", mysqlGetStart(page,rows));
		map.put("end", rows);
		List<Order> list = orderDao.selectOrderList(map);

		return new DataGrid(total, list);
	}
}
