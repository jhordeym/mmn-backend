package com.mmn.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmn.payment.model.entity.Product;
import com.mmn.payment.model.entity.Renovation;
import com.mmn.payment.repository.ProductRepository;

/*
 * listar produtos de subscrição
 */
@RestController
@RequestMapping("/subscriptionProducts")
public class SubscriptionProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public List<Product> findAll() {
		return this.productRepository.findByRenovationNot(Renovation.None);
	}
	
}
