package com.mmn.payment.service;

import org.springframework.stereotype.Service;

import com.mmn.payment.client.AccountClient;
import com.mmn.payment.model.entity.Payment;
import com.mmn.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final AccountClient accountClient;
    private final SubscriptionService subscriptionService;
	private final PaymentRepository paymentRepository;
	
	public Payment save(Payment payment) {
		payment = paymentRepository.save(payment);
		createSubscription(payment);
		accountClient.updateLevelActive(
				payment.getShoppingCart().getAccountId()
				);
		return payment;
	}

	private void createSubscription(Payment payment) {
		if (payment.getShoppingCart().isSubscrition()) {
			this.subscriptionService.createSubscription(payment.getShoppingCart().getAccountId(),
					payment.getShoppingCart().getProducts().iterator().next().getProduct());
		}
	}
	
}
