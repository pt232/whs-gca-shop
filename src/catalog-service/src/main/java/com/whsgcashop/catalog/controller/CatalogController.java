package com.whsgcashop.catalog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ThreadLocalRandom;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whsgcashop.catalog.model.Product;
import com.whsgcashop.catalog.service.CatalogService;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:3000")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;
    private static final Logger LOG = LoggerFactory.getLogger(CatalogController.class);

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @RateLimiter(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @Bulkhead(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @CircuitBreaker(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @Retry(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @TimeLimiter(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    public CompletionStage<List<Product>> getProducts() {
        LOG.info("Calling getProducts method inside CatalogController class");
        return CompletableFuture.supplyAsync(() -> catalogService.getProducts());
    }

    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @RateLimiter(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @Bulkhead(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @CircuitBreaker(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @Retry(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @TimeLimiter(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    public CompletionStage<Product> getProductById(@PathVariable(name = "productId") Integer productId) {
        LOG.info("Calling getProductById method inside CatalogController class");
        return CompletableFuture.supplyAsync(() -> catalogService.getProductById(productId));
    }

    @PutMapping(path = "/")
    @RateLimiter(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @Bulkhead(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @CircuitBreaker(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @Retry(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @TimeLimiter(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    public CompletionStage<String> updateProductActiveStatus() {
        LOG.info("Calling updateProductActiveStatus method inside CatalogController class");
        return CompletableFuture.supplyAsync(() -> catalogService.updateProductActiveStatus());
    }

    @DeleteMapping(path = "/{productId}")
    @RateLimiter(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @Bulkhead(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @CircuitBreaker(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @Retry(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    @TimeLimiter(name = "catalogService", fallbackMethod = "catalogServiceDefault")
    public CompletionStage<Product> deleteProduct(@PathVariable(name = "productId") Integer productId) {
        LOG.info("Calling deleteProduct method inside CatalogController class");
        return CompletableFuture.supplyAsync(() -> catalogService.deleteProduct(productId));
    }

    private CompletionStage<ResponseEntity<String>> catalogServiceDefault(Throwable throwable) {
        LOG.info("Calling catalogServiceDefault inside CatalogController class");
        ResponseEntity<String> response =
                new ResponseEntity<String>("The products could not be processed correctly.", HttpStatus.OK);
        return CompletableFuture.supplyAsync(() -> response);
    }

}
