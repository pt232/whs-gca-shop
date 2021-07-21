package com.whsgcashop.cart.service;

import java.util.ArrayList;
import java.util.List;

import com.whsgcashop.cart.utils.MyBasicAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whsgcashop.cart.model.Product;
import com.whsgcashop.cart.model.CartEntry;

@Service
public class CartService {

    @Autowired
    private RestTemplate restTemplate;
    private List<CartEntry> cartEntries = new ArrayList<CartEntry>();
    private static final Logger LOG = LoggerFactory.getLogger(CartService.class);

    @Value("${gca.host.property.value}")
    private String hostName;

    @Value("${gca.user.shipping.property.value}")
    private String shippingUser;
    @Value("${gca.password.shipping.property.value}")
    private String shippingPass;

    @Value("${gca.user.catalog.property.value}")
    private String catalogUser;
    @Value("${gca.password.catalog.property.value}")
    private String catalogPass;

    public Integer getEntriesAmount() {
        LOG.info("Calling getEntriesAmount method inside CartService class");
        return cartEntries.size();
    }

    public Double getTotalCost() {
        LOG.info("Calling getTotalCost method inside CartService class");

        double totalCost = 0.0;
        double shippingCost = restTemplate.exchange(hostName + "api/v1/shipping/",
                HttpMethod.GET, MyBasicAuth.getHeaders(shippingUser, shippingPass)
                , Double.class).getBody();

        totalCost += shippingCost;

        for (CartEntry cartEntry : cartEntries) {
            Product p = restTemplate.exchange(hostName + "api/v1/products/" + cartEntry.getProductId(),
                    HttpMethod.GET, MyBasicAuth.getHeaders(catalogUser, catalogPass),
                    Product.class).getBody();
            totalCost += p.getPrice();
        }

        return totalCost;
    }

    public List<Product> getCartEntries() {
        LOG.info("Calling getCartEntries method inside CartService class");

        List<Product> products = new ArrayList<Product>();

        for (CartEntry cartEntry : cartEntries) {
            Product p = restTemplate.exchange(hostName + "api/v1/products/" + cartEntry.getProductId(),
                    HttpMethod.GET, MyBasicAuth.getHeaders(catalogUser, catalogPass),
                    Product.class).getBody();
            products.add(p);
        }

        return products;
    }

    public CartEntry addEntry(CartEntry entry) {
        LOG.info("Calling addEntry method inside CartService class");

        boolean isInCart = cartEntries.stream().anyMatch(e -> e.getProductId() == entry.getProductId());
        if (!isInCart) {
            cartEntries.add(entry);
            return entry;
        }
        return null;
    }

    public String deleteCartEntries() {
        LOG.info("Calling deleteCartEntries method inside CartService class");

        restTemplate.exchange(hostName + "api/v1/products/", HttpMethod.PUT,
                MyBasicAuth.getHeaders(catalogUser, catalogPass), String.class);
        cartEntries.clear();

        return "Deleted Cart Entries";
    }

}
