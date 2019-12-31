package com.mmn.payment.service;

import com.mmn.payment.model.entity.CartProduct;
import com.mmn.payment.model.entity.Product;
import com.mmn.payment.model.entity.Subscription;
import com.mmn.payment.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription findLatestSubscriptionBy(final String accountId) {
        try {
            return subscriptionRepository.findByAccountIdOrderByStartDesc(
                    accountId,
                    PageRequest.of(0, 1)
            ).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }

	public void createSubscription(final String accountId, final Product product) {
		Subscription subscription = findLatestSubscriptionBy(accountId);
		subscription = subscription == null ? new Subscription() : subscription;
		subscription.setProduct(
				product
				);
		subscription.setAccountId(accountId);
		this.subscriptionRepository.save(
				subscription.toBegin()
				);
	}


}
