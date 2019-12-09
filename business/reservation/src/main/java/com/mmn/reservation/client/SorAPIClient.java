package com.mmn.reservation.client;

import feign.Headers;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

@FeignClient(
        name = "PayPalV1",
        url = "https://api.sandbox.paypal.com/v1/",
        configuration = SorAPIClient.PaypalClientV1Config.class
)
@Headers({"Content-Type: application/json", "Basic {clientId}:{secret}"})
public class SorAPIClient {

    class PaypalClientV1Config {

        @Bean
        @Autowired
        public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
            return null;
        }
    }
}
