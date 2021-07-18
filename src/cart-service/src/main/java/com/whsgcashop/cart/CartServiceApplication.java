package com.whsgcashop.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CartServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(CartServiceApplication.class);

    public static void main(String[] args) {
        LOG.info("Starting the Cart Service");
        SpringApplication.run(CartServiceApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.basicAuthentication("user1", "user1Pass").build();
    }

}
