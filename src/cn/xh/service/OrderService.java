package cn.xh.service;

import java.util.List;

import cn.xh.domain.Order;
import cn.xh.domain.User;

public interface OrderService {
	/**
	 *
	 * 
	 * @param o
	 */
	void genOrder(Order o);

	/**
	 * 
	 * 
	 * @param ordernum
	 * @return
	 */
	Order findOrderByNum(String ordernum);

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 */
	List<Order> findUserOrders(User user);

	List<Order> findOrders();

	void faHuo(String ordernum);
}
