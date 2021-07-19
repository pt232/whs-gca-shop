package com.whsgcashop.shipping.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whsgcashop.shipping.service.ShippingService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping(path = "/api/v1/shipping")
@CrossOrigin(origins = "http://localhost:3000")
public class ShippingController {

	@Autowired
	private ShippingService shippingService;
	private static final Logger LOG = LoggerFactory.getLogger(ShippingController.class);

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@RateLimiter(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	@Bulkhead(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	@CircuitBreaker(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	@Retry(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	@TimeLimiter(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	public CompletionStage<Double> getShippingCost() {
		LOG.info("Calling getShippingCost method inside ShippingController class shippingService");
		return CompletableFuture.supplyAsync(() -> shippingService.getShippingCost());
	}

	@GetMapping(path = "/tracking", produces = MediaType.APPLICATION_JSON_VALUE)
	@RateLimiter(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	@Bulkhead(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	@CircuitBreaker(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	@Retry(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	@TimeLimiter(name = "shippingService", fallbackMethod = "shippingServiceDefault")
	public CompletionStage<String> getTrackingNumber() {
		LOG.info("Calling getTrackingNumber method inside ShippingController class");
		return CompletableFuture.supplyAsync(() -> shippingService.getTrackingNumber());
	}

	private CompletionStage<ResponseEntity<String>> shippingServiceDefault(Throwable throwable) {
		LOG.info("Calling shippingServiceDefault inside ShippingController class");
		ResponseEntity<String> response =
				new ResponseEntity<String>("The shipping handling could not be processed correctly.", HttpStatus.OK);
		return CompletableFuture.supplyAsync(() -> response);
	}

}
