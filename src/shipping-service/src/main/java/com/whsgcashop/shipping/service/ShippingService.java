package com.whsgcashop.shipping.service;

import java.util.UUID;

import com.whsgcashop.shipping.utils.MyBasicAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whsgcashop.shipping.model.Product;

@Service
public class ShippingService {

    @Autowired
    private RestTemplate restTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(ShippingService.class);

    @Value("${gca.host.property.value}")
    private String hostName;

    @Value("${gca.user.cart.property.value}")
    private String cartUser;
    @Value("${gca.password.cart.property.value}")
    private String cartPass;

    public Double getShippingCost() {
        LOG.info("Calling getShippingCost method inside ShippingService class");

        Product[] products = restTemplate.exchange(hostName + "api/v1/cart/",
                HttpMethod.GET, MyBasicAuth.getHeaders(cartUser, cartPass),
                Product[].class).getBody();
        double totalCost = 0;

        for (Product p : products) {
            totalCost += p.getPrice();
        }

        return totalCost > 0 && totalCost <= 100 ? 10.0 : 0.0;
    }

    public String getTrackingNumber() {
        LOG.info("Calling getTrackingNumber method inside ShippingService class");
        return UUID.randomUUID().toString();
    }

}
