package cn.xh.dao;

import java.util.List;

import cn.xh.domain.Order;

public interface OrdetrDao {

	/**
	 * 
	 * 
	 * @param o
	 */
	void save(Order o);

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
	 * @param id
	 * @return
	 */
	List<Order> findOrdersByUser(String userId);

	List<Order> findOrders();

	void faHuo(String ordernum);

}
