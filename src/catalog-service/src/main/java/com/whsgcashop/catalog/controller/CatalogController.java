package com.whsgcashop.catalog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOG = LoggerFactory.getLogger(CatalogController.class);

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProducts() {
		LOG.info("Calling getProducts method inside CatalogController class");
		return catalogService.getProducts();
	}

	@GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getProductById(@PathVariable(name = "productId") Integer productId) {
		LOG.info("Calling getProductById method inside CatalogController class");
		return catalogService.getProductById(productId);
	}
	
	@PutMapping(path = "/")
	public void updateProductActiveStatus() {
		LOG.info("Calling updateProductActiveStatus method inside CatalogController class");
		catalogService.updateProductActiveStatus();
	}

	@DeleteMapping(path = "/{productId}")
	public void deleteProduct(@PathVariable(name = "productId") Integer productId) {
		LOG.info("Calling deleteProduct method inside CatalogController class");
		catalogService.deleteProduct(productId);
	}

}
