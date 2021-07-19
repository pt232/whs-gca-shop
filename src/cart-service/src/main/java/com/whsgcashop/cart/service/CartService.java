package com.whsgcashop.cart.service;

import java.util.ArrayList;
import java.util.List;

import com.whsgcashop.cart.utils.ResilienceUtils;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whsgcashop.cart.model.Product;
import com.whsgcashop.cart.model.CartEntry;

@Service
public class CartService {

	@Autowired
	private RestTemplate restTemplate;
	private List<CartEntry> cartEntries = new ArrayList<CartEntry>();
	private static final Logger LOG = LoggerFactory.getLogger(CartService.class);

	public Integer getEntriesAmount() {
		LOG.info("Calling getEntriesAmount method inside CartService class");

		ResilienceUtils.randomTimeout();
		ResilienceUtils.randomFail();

		return cartEntries.size();
	}

	public Double getTotalCost() {
		LOG.info("Calling getTotalCost method inside CartService class");

		ResilienceUtils.randomTimeout();
		ResilienceUtils.randomFail();

		double totalCost = 0.0;
		double shippingCost = restTemplate.getForObject("http://localhost:8082/api/v1/shipping/", Double.class);

		totalCost += shippingCost;

		for (CartEntry cartEntry : cartEntries) {
			Product p = restTemplate.getForObject("http://localhost:8080/api/v1/products/" + cartEntry.getProductId(),
					Product.class);
			totalCost += p.getPrice();
		}

		return totalCost;
	}

	public List<Product> getCartEntries() {
		LOG.info("Calling getCartEntries method inside CartService class");

		ResilienceUtils.randomTimeout();
		ResilienceUtils.randomFail();

		List<Product> products = new ArrayList<Product>();

		for (CartEntry cartEntry : cartEntries) {
			Product p = restTemplate.getForObject("http://localhost:8080/api/v1/products/" + cartEntry.getProductId(),
					Product.class);
			products.add(p);
		}

		return products;
	}

	public CartEntry addEntry(CartEntry entry) {
		LOG.info("Calling addEntry method inside CartService class");

		ResilienceUtils.randomTimeout();
		ResilienceUtils.randomFail();

		boolean isInCart = cartEntries.stream().anyMatch(e -> e.getProductId() == entry.getProductId());
		if (!isInCart) {
			cartEntries.add(entry);
			return entry;
		}
		return null;
	}

	public String deleteCartEntries() {
		LOG.info("Calling deleteCartEntries method inside CartService class");

		ResilienceUtils.randomTimeout();
		ResilienceUtils.randomFail();

		restTemplate.put("http://localhost:8080/api/v1/products/", null);
		cartEntries.clear();

		return "Deleted Cart Entries";
	}

}
