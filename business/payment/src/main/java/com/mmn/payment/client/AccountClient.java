package com.mmn.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.mmn.payment.client.fallback.AccountClientFallBack;

@FeignClient(
        name = "${MMN_BUSINESS_ACCOUNT_NAME:business-account-service}",
        fallback = AccountClientFallBack.class
)
public interface AccountClient {

	@PutMapping("/level/{id}")
    public void atualizaLevelActive(@PathVariable("id") String accountId);
	
}
