package com.mmn.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmn.account.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
