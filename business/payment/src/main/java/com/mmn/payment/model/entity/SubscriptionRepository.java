package com.mmn.payment.model.entity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

	List<Subscription> findByAccountIdOrderByStartDesc(String accountId, Pageable pageable);

	
}
