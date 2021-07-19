package com.whsgcashop.checkout.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.ThreadLocalRandom;

public class ResilienceUtils {

    public static void randomTimeout() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void randomFail() {
        int random = ThreadLocalRandom.current().nextInt(1, 4);
        if (random < 2) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (random < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
