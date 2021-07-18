package com.whsgcashop.shipping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whsgcashop.shipping.service.ShippingService;

@RestController
@RequestMapping(path = "/api/v1/shipping")
@CrossOrigin(origins = "http://localhost:3000")
public class ShippingController {

	@Autowired
	private ShippingService shippingService;

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Double getShippingCost() {
		return shippingService.getShippingCost();
	}

	@GetMapping(path = "/tracking", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getTrackingNumber() {
		return shippingService.getTrackingNumber();
	}

}
