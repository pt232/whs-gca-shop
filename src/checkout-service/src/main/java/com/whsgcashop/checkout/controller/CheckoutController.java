package com.whsgcashop.checkout.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOG = LoggerFactory.getLogger(CheckoutController.class);

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTemplate getOrder() {
		LOG.info("Calling getOrder method inside CheckoutController class");
		return checkoutService.getOrder();
	}
	
	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTemplate createOrder(@RequestBody Order order) {
		LOG.info("Calling createOrder method inside CheckoutController class");
		return checkoutService.createOrder(order);
	}

}
