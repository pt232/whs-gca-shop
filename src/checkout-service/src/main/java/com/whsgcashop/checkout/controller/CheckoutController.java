package com.whsgcashop.checkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whsgcashop.checkout.model.Order;
import com.whsgcashop.checkout.model.ResponseTemplate;
import com.whsgcashop.checkout.service.CheckoutService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/v1/checkout")
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTemplate getOrder() {
		return checkoutService.getOrder();
	}
	
	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTemplate createOrder(@RequestBody Order order) {
		return checkoutService.createOrder(order);
	}

}
