package com.xingzhe.mcb.service.impl;

import com.xingzhe.mcb.dao.OrderDao;
import com.xingzhe.mcb.domain.Order;
import com.xingzhe.mcb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 陆凤良
 * Date: 13-10-11
 * Time: 下午9:56
 * 公司：三江行者工作室
 * To change this template use File | Settings | File Templates.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao<Order> orderDao;
	@Override
	public List<Order> selectOrderList(Map<String, Object> map) {
		return orderDao.selectOrderList(map);
	}

	@Override
	public int selectOrderCount(Map<String, Object> map) {
		return orderDao.selectOrderCount(map);
	}

}
