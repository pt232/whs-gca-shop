package com.whsgcashop.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProducts() {
		return catalogService.getProducts();
	}

	@GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getProductById(@PathVariable(name = "productId") Integer productId) {
		return catalogService.getProductById(productId);
	}
	
	@PutMapping(path = "/")
	public void updateProductActiveStatus() {
		catalogService.updateProductActiveStatus();
	}

	@DeleteMapping(path = "/{productId}")
	public void deleteProduct(@PathVariable(name = "productId") Integer productId) {
		catalogService.deleteProduct(productId);
	}

}
