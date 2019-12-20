package cn.xh.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8387723760179487034L;
	private String ordernum;
	private int quantity;
	private double money;
	private String time;
	private int status;
	private User user;

	private List<Orderitem> items = new ArrayList<Orderitem>();

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Orderitem> getItems() {
		return items;
	}

	public void setItems(List<Orderitem> items) {
		this.items = items;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String ordernum, int quantity, double money, String time, int status, User user,
			List<Orderitem> items) {
		super();
		this.ordernum = ordernum;
		this.quantity = quantity;
		this.money = money;
		this.time = time;
		this.status = status;
		this.user = user;
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [ordernum=" + ordernum + ", quantity=" + quantity + ", money=" + money + ", time=" + time
				+ ", status=" + status + ", user=" + user + ", items=" + items + "]";
	}

}
