package com.mmn.payment.repository;

import com.mmn.payment.model.entity.Product;
import com.mmn.payment.model.entity.Renovation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByRenovationNot(final Renovation renovationNone);
}
