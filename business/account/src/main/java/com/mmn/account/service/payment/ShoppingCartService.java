package com.mmn.account.service.payment;

import com.mmn.account.model.payment.Product;
import com.mmn.account.model.payment.ShoppingCart;
import com.mmn.account.repository.payment.CartProductRepository;
import com.mmn.account.repository.payment.ProductRepository;
import com.mmn.account.repository.payment.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;

    public ShoppingCart save(final ShoppingCart cart) {
        final ShoppingCart save = shoppingCartRepository.save(cart);
        save.getProducts().stream().forEach(cartProduct -> {
            cartProduct.setShoppingCart(save);
            final Optional<Product> byId = productRepository.findById(cartProduct.getProduct().getId());
            cartProduct.setProduct(byId.get());
            cartProductRepository.save(cartProduct);
        });
        return save;
    }
}
