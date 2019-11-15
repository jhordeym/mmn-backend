package com.mmn.account.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class CartProduct {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@ManyToOne
	private Product product;
	@ManyToOne
	private ShoppingCart shoppingCart;
	private BigDecimal quantity = BigDecimal.ONE;
	private BigDecimal price;
	private BigDecimal discount;
}
