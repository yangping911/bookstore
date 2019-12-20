package cn.xh.web.formbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.xh.domain.Book;

//ﳵ
public class Cart implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1961314881033087098L;
	private Map<String, CartItem> itmes = new HashMap<String, CartItem>();
	private int totalQuantity;
	private float totalMoney;

	public int getTotalQuantity() {
		totalQuantity = 0;
		for (Map.Entry<String, CartItem> me : itmes.entrySet()) {
			totalQuantity += me.getValue().getQuantity();
		}
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalMoney() {
		totalMoney = 0;
		for (Map.Entry<String, CartItem> me : itmes.entrySet()) {
			totalMoney += me.getValue().getMoney();
		}
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Map<String, CartItem> getItmes() {
		return itmes;
	}

	
	public void addBook(Book book) {
		if (itmes.containsKey(book.getBook_id())) {
			
			CartItem item = itmes.get(book.getBook_id());
			item.setQuantity(item.getQuantity() + 1);
		} else {
			CartItem item = new CartItem(book);
			item.setQuantity(1);
			itmes.put(book.getBook_id(), item);
		}
	}

}
