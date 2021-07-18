package com.whsgcashop.checkout.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whsgcashop.checkout.model.Order;
import com.whsgcashop.checkout.model.Product;
import com.whsgcashop.checkout.model.ResponseTemplate;

@Service
public class CheckoutService {

	@Autowired
	private RestTemplate restTemplate;
	private List<ResponseTemplate> resList = new ArrayList<ResponseTemplate>();

	public ResponseTemplate getOrder() {
		return resList.get(resList.size() - 1);
	}

	public ResponseTemplate createOrder(Order order) {
		ResponseTemplate resTemplate = new ResponseTemplate();

		String mailRegex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
		String zipcodeRegex = "\\d{4,5}";
		String creditCardRegex = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";
		String cvvRegex = "\\d{3}";

		if (order.getEmail() == null || order.getStreet() == null || order.getZipcode() == null
				|| order.getCity() == null || order.getState() == null || order.getCountry() == null
				|| order.getCreditCardNumber() == null || order.getMonth() == null || (Integer) order.getYear() == null
				|| order.getCvv() == null) {
			resTemplate.setSuccess(false);
			resTemplate.setMessage("One or more fields are empty.");
			return resTemplate;
		}

		if (!validateUserInput(mailRegex, order.getEmail())) {
			resTemplate.setSuccess(false);
			resTemplate.setMessage("Plase enter a valid e-mail address.");
			return resTemplate;
		}

		if (!validateUserInput(zipcodeRegex, order.getZipcode())) {
			resTemplate.setSuccess(false);
			resTemplate.setMessage("Plase enter a valid zip code.");
			return resTemplate;
		}

		if (!validateUserInput(creditCardRegex, order.getCreditCardNumber())
				|| order.getCreditCardNumber().equals("0000-0000-0000-0000")) {
			resTemplate.setSuccess(false);
			resTemplate.setMessage("Plase enter a valid credit card number.");
			return resTemplate;
		}

		if (!validateUserInput(cvvRegex, order.getCvv())) {
			resTemplate.setSuccess(false);
			resTemplate.setMessage("Plase enter a valid CVV.");
			return resTemplate;
		}

		if (!validateDate(order.getMonth(), order.getYear())) {
			resTemplate.setSuccess(false);
			resTemplate.setMessage("Plase enter a valid time period.");
			return resTemplate;
		}

		Product[] products = restTemplate.getForObject("http://localhost:8081/api/v1/cart/", Product[].class);
		String trackingNumber = restTemplate.getForObject("http://localhost:8082/api/v1/shipping/tracking/",
				String.class);
		Double shippingCost = restTemplate.getForObject("http://localhost:8082/api/v1/shipping/", Double.class);
		Double totalCost = restTemplate.getForObject("http://localhost:8081/api/v1/cart/cost/", Double.class);

		order.setTrackingNumber(trackingNumber);
		order.setOrderNumber(generateOrderNumber());
		order.setShippingCost(shippingCost);
		order.setTotalCost(totalCost);

		restTemplate.delete("http://localhost:8081/api/v1/cart/");

		resTemplate.setSuccess(true);
		resTemplate.setMessage("Everything went fine.");
		resTemplate.setOrder(order);
		resTemplate.setProducts(Arrays.asList(products));

		resList.add(resTemplate);

		return resTemplate;
	}

	private boolean validateUserInput(String regex, String input) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	private boolean validateDate(String month, int year) {
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"Oktober", "November", "December" };
		return Arrays.asList(months).contains(month) && year >= 2021 && year <= 2025;
	}

	private String generateOrderNumber() {
		int low = 100000000;
		int high = 999999999;
		int randomNumber = low + (int) (Math.random() * (high - low + 1));

		StringBuilder orderNumber = new StringBuilder(String.valueOf(randomNumber));

		orderNumber.setCharAt(2, '-');
		orderNumber.setCharAt(6, '-');

		return orderNumber.toString();
	}

}