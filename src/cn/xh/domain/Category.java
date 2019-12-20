package cn.xh.domain;

import java.io.Serializable;


public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2653973744287165074L;
	private String category_id;
	private String category_name;
	private String category_desc;

	public Category() {
		super();
	}

	public Category(String category_id, String category_name, String category_desc) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.category_desc = category_desc;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_desc() {
		return category_desc;
	}

	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}

	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name + ", category_desc="
				+ category_desc + "]";
	}
}
