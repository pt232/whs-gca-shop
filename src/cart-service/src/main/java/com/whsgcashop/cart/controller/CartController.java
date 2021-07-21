package com.whsgcashop.cart.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

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
@CrossOrigin(origins = "http://gca-shop.local")
public class CartController {

    @Autowired
    private CartService cartService;
    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

    @GetMapping(path = "/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletionStage<Integer> getCartEntriesAmount() {
        LOG.info("Calling getCartEntriesAmount method inside CartController class");
        return CompletableFuture.supplyAsync(() -> cartService.getEntriesAmount());
    }

    @GetMapping(path = "/cost", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletionStage<Double> getTotalCost() {
        LOG.info("Calling getTotalCost method inside CartController class");
        return CompletableFuture.supplyAsync(() -> cartService.getTotalCost());
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletionStage<List<Product>> getCartEntries() {
        LOG.info("Calling getCartEntries method inside CartController class");
        return CompletableFuture.supplyAsync(() -> cartService.getCartEntries());
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletionStage<CartEntry> addCartEntry(@RequestBody CartEntry entry) {
        LOG.info("Calling addCartEntry method inside CartController class");
        return CompletableFuture.supplyAsync(() -> cartService.addEntry(entry));
    }

    @DeleteMapping(path = "/")
    public CompletionStage<String> deleteCartEntries() {
        LOG.info("Calling deleteCartEntries method inside CartController class");
        return CompletableFuture.supplyAsync(() -> cartService.deleteCartEntries());
    }

}
