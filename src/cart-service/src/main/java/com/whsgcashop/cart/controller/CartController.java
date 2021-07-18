package com.whsgcashop.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whsgcashop.cart.model.CartEntry;
import com.whsgcashop.cart.model.Product;
import com.whsgcashop.cart.service.CartService;

@RestController
@RequestMapping(path = "/api/v1/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

	@Autowired
	private CartService cartService;
	private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

	@GetMapping(path = "/amount", produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getCartEntriesAmount() {
		LOG.info("Calling getCartEntriesAmount method inside CartController class");
		return cartService.getEntriesAmount();
	}
	
	@GetMapping(path = "/cost", produces = MediaType.APPLICATION_JSON_VALUE)
	public Double getTotalCost() {
		LOG.info("Calling getTotalCost method inside CartController class");
		return cartService.getTotalCost();
	}

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getCartEntries() {
		LOG.info("Calling getCartEntries method inside CartController class");
		return cartService.getCartEntries();
	}
	
	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CartEntry addCartEntry(@RequestBody CartEntry entry) {
		LOG.info("Calling addCartEntry method inside CartController class");
		return cartService.addEntry(entry);
	}
	
	@DeleteMapping(path = "/")
	public void deleteCartEntries() {
		LOG.info("Calling deleteCartEntries method inside CartController class");
		cartService.deleteCartEntries();
	}
	
}
