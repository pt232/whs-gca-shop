package com.whsgcashop.checkout.utils;

import com.whsgcashop.checkout.CheckoutServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.ThreadLocalRandom;

public class ResilienceUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ResilienceUtils.class);

    public static void randomTimeout() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void randomFail() {
        int random = ThreadLocalRandom.current().nextInt(1, 6);
        if (random < 2) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (random < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
