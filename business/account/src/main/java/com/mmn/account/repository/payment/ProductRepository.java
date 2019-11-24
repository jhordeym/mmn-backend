package com.mmn.account.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmn.account.model.payment.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
