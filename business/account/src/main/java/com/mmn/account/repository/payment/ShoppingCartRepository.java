package com.mmn.account.repository.payment;

import com.mmn.account.model.payment.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
}
