package com.whsgcashop.checkout.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whsgcashop.checkout.model.Order;
import com.whsgcashop.checkout.model.ResponseTemplate;
import com.whsgcashop.checkout.service.CheckoutService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/v1/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;
    private static final Logger LOG = LoggerFactory.getLogger(CheckoutController.class);

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletionStage<ResponseTemplate> getOrder() {
        LOG.info("Calling getOrder method inside CheckoutController class");
        return CompletableFuture.supplyAsync(() -> checkoutService.getOrder());
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RateLimiter(name = "checkoutService", fallbackMethod = "checkoutServiceDefault")
    @Bulkhead(name = "checkoutService", fallbackMethod = "checkoutServiceDefault")
    @CircuitBreaker(name = "checkoutService", fallbackMethod = "checkoutServiceDefault")
    @Retry(name = "checkoutService", fallbackMethod = "checkoutServiceDefault")
    public CompletionStage<ResponseTemplate> createOrder(@RequestBody Order order) {
        LOG.info("Calling createOrder method inside CheckoutController class");
        return CompletableFuture.supplyAsync(() -> checkoutService.createOrder(order));
    }

    private CompletionStage<ResponseEntity<String>> checkoutServiceDefault(Throwable throwable) {
        LOG.info("Calling checkoutServiceDefault inside CheckoutController class");
        ResponseEntity<String> response =
                new ResponseEntity<String>("The checkout could not be processed correctly.", HttpStatus.OK);
        return CompletableFuture.supplyAsync(() -> response);
    }

}
