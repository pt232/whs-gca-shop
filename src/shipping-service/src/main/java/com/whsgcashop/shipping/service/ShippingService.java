package com.whsgcashop.shipping.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whsgcashop.shipping.model.Product;

@Service
public class ShippingService {

	@Autowired
	private RestTemplate restTemplate;

	public Double getShippingCost() {
		Product[] products = restTemplate.getForObject("http://localhost:8081/api/v1/cart/", Product[].class);
		double totalCost = 0;

		for (Product p : products) {
			totalCost += p.getPrice();
		}

		return totalCost > 0 && totalCost <= 100 ? 10.0 : 0.0;
	}

	public String getTrackingNumber() {
		return UUID.randomUUID().toString();
	}

}
