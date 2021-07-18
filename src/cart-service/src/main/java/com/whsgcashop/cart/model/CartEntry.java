package com.whsgcashop.cart.model;

public class CartEntry {

	private int productId;

	public CartEntry() {
		
	}
	
	public CartEntry(int productId) {
		this.productId = productId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
