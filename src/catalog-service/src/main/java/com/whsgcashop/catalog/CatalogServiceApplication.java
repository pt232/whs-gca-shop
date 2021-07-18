package com.whsgcashop.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(CatalogServiceApplication.class);

	public static void main(String[] args) {
		LOG.info("Starting the Catalog Service");
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

}
