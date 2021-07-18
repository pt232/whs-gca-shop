package com.whsgcashop.checkout.model;

public class Product {

	private int id;
	private String image;
	private String name;
	private double price;

	public Product() {

	}

	public Product(int id, String image, String name, double price) {
		this.id = id;
		this.image = image;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
