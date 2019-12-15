package com.mmn.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mmn.payment.model.entity.Subscription;
import com.mmn.payment.model.entity.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public Subscription findLatestSubscriptionBy(String accountId) {
		try {
			return subscriptionRepository.findByAccountIdOrderByStartDesc(
					accountId, 
					PageRequest.of(0, 1)
					).iterator().next();
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
