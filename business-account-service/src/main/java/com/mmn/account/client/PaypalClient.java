package com.mmn.account.client;

import com.mmn.account.model.paypal.PaypalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name="payPal",
        url = "${app.payment.feign.url}"
)
public interface PaypalClient {
    @PostMapping("billing/plans")
    PaypalResponse createSubscription();

    @PostMapping("catalogs/products")
    PaypalResponse createResponse();
}
