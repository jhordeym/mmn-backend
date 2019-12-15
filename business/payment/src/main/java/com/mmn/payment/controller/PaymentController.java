package com.mmn.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmn.payment.client.AccountClient;
import com.mmn.payment.model.entity.Payment;
import com.mmn.payment.repository.PaymentRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/payments")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentRepository paymentRepository;
	private final AccountClient accountClient;
	
	public Payment save(@RequestBody Payment payment) {
		payment = paymentRepository.save(payment);
		accountClient.atualizaLevelActive(
				payment.getShoppingCart().getAccountId()
				);
		return payment;
	}
}
