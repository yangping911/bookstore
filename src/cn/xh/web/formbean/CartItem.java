package cn.xh.web.formbean;

import java.io.Serializable;

import cn.xh.domain.Book;


public class CartItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -490020551093799084L;
	private Book book;
	private int quantity;
	private float money;

	public CartItem(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getMoney() {
		return book.getBook_price() * quantity;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public Book getBook() {
		return book;
	}

}
