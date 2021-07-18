package com.whsgcashop.checkout.model;

public class Order {

	private String orderNumber;
	private String trackingNumber;
	private Double shippingCost;
	private Double totalCost;
	private String email;
	private String street;
	private String zipcode;
	private String city;
	private String state;
	private String country;
	private String creditCardNumber;
	private String month;
	private int year;
	private String cvv;

	public Order() {

	}

	public Order(String orderNumber, String trackingNumber, Double shippingCost, Double totalCost, String email,
			String street, String zipcode, String city, String state, String country, String creditCardNumber,
			String month, int year, String cvv) {
		this.orderNumber = orderNumber;
		this.trackingNumber = trackingNumber;
		this.shippingCost = shippingCost;
		this.totalCost = totalCost;
		this.email = email;
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
		this.country = country;
		this.creditCardNumber = creditCardNumber;
		this.month = month;
		this.year = year;
		this.cvv = cvv;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

}
