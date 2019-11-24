package com.mmn.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmn.payment.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
