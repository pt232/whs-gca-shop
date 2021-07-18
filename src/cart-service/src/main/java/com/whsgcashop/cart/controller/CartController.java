package com.whsgcashop.cart.controller;

import java.util.List;

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

	@GetMapping(path = "/amount", produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getCartEntriesAmount() {
		return cartService.getEntriesAmount();
	}
	
	@GetMapping(path = "/cost", produces = MediaType.APPLICATION_JSON_VALUE)
	public Double getTotalCost() {
		return cartService.getTotalCost();
	}

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getCartEntries() {
		return cartService.getCartEntries();
	}
	
	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CartEntry addCartEntry(@RequestBody CartEntry entry) {
		return cartService.addEntry(entry);
	}
	
	@DeleteMapping(path = "/")
	public void deleteCartEntries() {
		cartService.deleteCartEntries();
	}
	
}
