package com.mmn.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmn.payment.model.entity.Subscription;
import com.mmn.payment.service.SubscriptionService;

@RestController
@CrossOrigin("*")
@RequestMapping("/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping("/{id}")
	public Subscription findLatestSubscriptionBy(@PathVariable("id") String accountId) {
		return subscriptionService.findLatestSubscriptionBy(accountId);
	}

}
