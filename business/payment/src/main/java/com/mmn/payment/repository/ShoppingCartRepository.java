package com.mmn.payment.repository;

import com.mmn.payment.model.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
}
