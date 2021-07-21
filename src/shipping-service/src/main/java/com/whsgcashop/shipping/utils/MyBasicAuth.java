package com.whsgcashop.shipping.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class MyBasicAuth {

    public static HttpEntity getHeaders(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        return new HttpEntity(headers);
    }

}
