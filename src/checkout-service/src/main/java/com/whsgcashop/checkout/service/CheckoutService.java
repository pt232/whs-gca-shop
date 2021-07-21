package com.whsgcashop.checkout.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.whsgcashop.checkout.utils.MyBasicAuth;
import com.whsgcashop.checkout.utils.ResilienceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whsgcashop.checkout.model.Order;
import com.whsgcashop.checkout.model.Product;
import com.whsgcashop.checkout.model.ResponseTemplate;

@Service
public class CheckoutService {

    @Autowired
    private RestTemplate restTemplate;
    private List<ResponseTemplate> resList = new ArrayList<ResponseTemplate>();
    private static final Logger LOG = LoggerFactory.getLogger(CheckoutService.class);

    @Value("${gca.host.property.value}")
    private String hostName;

    @Value("${gca.user.cart.property.value}")
    private String cartUser;
    @Value("${gca.password.cart.property.value}")
    private String cartPass;

    @Value("${gca.user.shipping.property.value}")
    private String shippingUser;
    @Value("${gca.password.shipping.property.value}")
    private String shippingPass;

    public ResponseTemplate getOrder() {
        LOG.info("Calling getOrder method inside CheckoutService class");
        return resList.get(resList.size() - 1);
    }

    public ResponseTemplate createOrder(Order order) {
        LOG.info("Calling createOrder method inside CheckoutService class");

        ResilienceUtils.randomFail();

        ResponseTemplate resTemplate = new ResponseTemplate();

        String mailRegex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
        String zipcodeRegex = "\\d{4,5}";
        String creditCardRegex = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";
        String cvvRegex = "\\d{3}";

        if (order.getEmail() == null || order.getStreet() == null || order.getZipcode() == null
                || order.getCity() == null || order.getState() == null || order.getCountry() == null
                || order.getCreditCardNumber() == null || order.getMonth() == null || (Integer) order.getYear() == null
                || order.getCvv() == null) {
            resTemplate.setSuccess(false);
            resTemplate.setMessage("One or more fields are empty.");
            return resTemplate;
        }

        if (!validateUserInput(mailRegex, order.getEmail())) {
            resTemplate.setSuccess(false);
            resTemplate.setMessage("Please enter a valid e-mail address.");
            return resTemplate;
        }

        if (!validateUserInput(zipcodeRegex, order.getZipcode())) {
            resTemplate.setSuccess(false);
            resTemplate.setMessage("Please enter a valid zip code.");
            return resTemplate;
        }

        if (!validateUserInput(creditCardRegex, order.getCreditCardNumber())
                || order.getCreditCardNumber().equals("0000-0000-0000-0000")) {
            resTemplate.setSuccess(false);
            resTemplate.setMessage("Please enter a valid credit card number.");
            return resTemplate;
        }

        if (!validateUserInput(cvvRegex, order.getCvv())) {
            resTemplate.setSuccess(false);
            resTemplate.setMessage("Please enter a valid CVV.");
            return resTemplate;
        }

        if (!validateDate(order.getMonth(), order.getYear())) {
            resTemplate.setSuccess(false);
            resTemplate.setMessage("Please enter a valid time period.");
            return resTemplate;
        }

        Product[] products = restTemplate.exchange(hostName + "api/v1/cart/",
                HttpMethod.GET, MyBasicAuth.getHeaders(cartUser, cartPass),
                Product[].class).getBody();
        String trackingNumber = restTemplate.exchange(hostName + "api/v1/shipping/tracking/",
                HttpMethod.GET, MyBasicAuth.getHeaders(shippingUser, shippingPass),
                String.class).getBody();
        Double shippingCost = restTemplate.exchange(hostName + "api/v1/shipping/",
                HttpMethod.GET, MyBasicAuth.getHeaders(shippingUser, shippingPass),
                Double.class).getBody();
        Double totalCost = restTemplate.exchange(hostName + "api/v1/cart/cost/",
                HttpMethod.GET, MyBasicAuth.getHeaders(cartUser, cartPass),
                Double.class).getBody();

        order.setTrackingNumber(trackingNumber);
        order.setOrderNumber(generateOrderNumber());
        order.setShippingCost(shippingCost);
        order.setTotalCost(totalCost);

        restTemplate.exchange(hostName + "api/v1/cart/", HttpMethod.DELETE,
                MyBasicAuth.getHeaders(cartUser, cartPass),
                String.class);

        resTemplate.setSuccess(true);
        resTemplate.setMessage("Everything went fine.");
        resTemplate.setOrder(order);
        resTemplate.setProducts(Arrays.asList(products));

        resList.add(resTemplate);

        return resTemplate;
    }

    private boolean validateUserInput(String regex, String input) {
        LOG.info("Calling validateUserInput method inside CheckoutService class");

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private boolean validateDate(String month, int year) {
        LOG.info("Calling validateDate method inside CheckoutService class");

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
                "Oktober", "November", "December"};
        return Arrays.asList(months).contains(month) && year >= 2021 && year <= 2025;
    }

    private String generateOrderNumber() {
        LOG.info("Calling generateOrderNumber method inside CheckoutService class");

        int low = 100000000;
        int high = 999999999;
        int randomNumber = low + (int) (Math.random() * (high - low + 1));

        StringBuilder orderNumber = new StringBuilder(String.valueOf(randomNumber));

        orderNumber.setCharAt(2, '-');
        orderNumber.setCharAt(6, '-');

        return orderNumber.toString();
    }

}
