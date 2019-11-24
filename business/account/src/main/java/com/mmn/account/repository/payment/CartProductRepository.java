package com.mmn.account.repository.payment;

import com.mmn.account.model.payment.CartProduct;
import com.mmn.account.model.payment.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, String> {
}
