package com.whsgcashop.checkout.model;

import java.util.List;

public class ResponseTemplate {

	private boolean success;
	private String message;
	private Order order;
	private List<Product> products;

	public ResponseTemplate() {

	}

	public ResponseTemplate(boolean success, String message, Order order, List<Product> products) {
		this.success = success;
		this.message = message;
		this.order = order;
		this.products = products;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
