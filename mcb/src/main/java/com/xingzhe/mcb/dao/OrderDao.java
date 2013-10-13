package com.xingzhe.mcb.dao;

import com.xingzhe.framework.dao.BaseDao;
import com.xingzhe.mcb.domain.Order;
import com.xingzhe.mcb.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDao<T extends Order> extends BaseDao<T> {
	@Autowired
	private OrderMapper orderMapper;

	/**
	 * 添加订单
	 *
	 * @param customerId
	 * @param productList
	 */
	public void addorder(int customerId, String productList) {
		orderMapper.addOrder(customerId, productList);
	}

	public List<Order> selectOrderList(Map<String, Object> map) {
		return orderMapper.selectOrderList(map);
	}

	public int selectOrderCount(Map<String, Object> map) {
		return orderMapper.selectOrderCount(map);
	}

}
