package com.whsgcashop.shipping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whsgcashop.shipping.service.ShippingService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping(path = "/api/v1/shipping")
@CrossOrigin(origins = "http://gca-shop.local")
public class ShippingController {

	@Autowired
	private ShippingService shippingService;
	private static final Logger LOG = LoggerFactory.getLogger(ShippingController.class);

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompletionStage<Double> getShippingCost() {
		LOG.info("Calling getShippingCost method inside ShippingController");
		return CompletableFuture.supplyAsync(() -> shippingService.getShippingCost());
	}

	@GetMapping(path = "/tracking", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompletionStage<String> getTrackingNumber() {
		LOG.info("Calling getTrackingNumber method inside ShippingController class");
		return CompletableFuture.supplyAsync(() -> shippingService.getTrackingNumber());
	}

}
