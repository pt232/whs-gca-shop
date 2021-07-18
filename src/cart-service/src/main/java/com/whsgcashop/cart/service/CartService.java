package com.whsgcashop.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whsgcashop.cart.model.Product;
import com.whsgcashop.cart.model.CartEntry;

@Service
public class CartService {

	@Autowired
	private RestTemplate restTemplate;
	private List<CartEntry> cartEntries = new ArrayList<CartEntry>();

	public Integer getEntriesAmount() {
		return cartEntries.size();
	}

	public Double getTotalCost() {
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
		List<Product> products = new ArrayList<Product>();

		for (CartEntry cartEntry : cartEntries) {
			Product p = restTemplate.getForObject("http://localhost:8080/api/v1/products/" + cartEntry.getProductId(),
					Product.class);
			products.add(p);
		}

		return products;
	}

	public CartEntry addEntry(CartEntry entry) {
		boolean isInCart = cartEntries.stream().anyMatch(e -> e.getProductId() == entry.getProductId());
		if (!isInCart) {
			cartEntries.add(entry);
			return entry;
		}
		return null;
	}

	public void deleteCartEntries() {
		restTemplate.put("http://localhost:8080/api/v1/products/", null);
		cartEntries.clear();
	}

}
