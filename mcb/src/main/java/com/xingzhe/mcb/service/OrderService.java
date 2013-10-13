package com.xingzhe.mcb.service;

import com.xingzhe.mcb.domain.Order;

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
public interface OrderService {
	List<Order> selectOrderList(Map<String, Object> map);

	int selectOrderCount(Map<String, Object> map);

}
