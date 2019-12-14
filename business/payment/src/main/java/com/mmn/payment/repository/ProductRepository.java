package com.mmn.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmn.payment.model.entity.Product;
import com.mmn.payment.model.entity.Renovation;

public interface ProductRepository extends JpaRepository<Product, String> {

	List<Product> findByRenovationNot(Renovation renovationNone);

}
