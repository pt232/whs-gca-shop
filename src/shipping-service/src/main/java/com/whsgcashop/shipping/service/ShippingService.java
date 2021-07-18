package com.whsgcashop.shipping.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whsgcashop.shipping.model.Product;

@Service
public class ShippingService {

	@Autowired
	private RestTemplate restTemplate;
	private static final Logger LOG = LoggerFactory.getLogger(ShippingService.class);

	public Double getShippingCost() {
		LOG.info("Calling getShippingCost method inside ShippingService class");

		Product[] products = restTemplate.getForObject("http://localhost:8081/api/v1/cart/", Product[].class);
		double totalCost = 0;

		for (Product p : products) {
			totalCost += p.getPrice();
		}

		return totalCost > 0 && totalCost <= 100 ? 10.0 : 0.0;
	}

	public String getTrackingNumber() {
		LOG.info("Calling getTrackingNumber method inside ShippingService class");
		return UUID.randomUUID().toString();
	}

}
