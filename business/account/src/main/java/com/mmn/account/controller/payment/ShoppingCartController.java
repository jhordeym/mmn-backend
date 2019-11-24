package com.mmn.account.controller.payment;

import com.mmn.account.model.payment.ShoppingCart;
import com.mmn.account.repository.payment.ShoppingCartRepository;
import com.mmn.account.service.payment.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/shopping-cart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService service;

    @PostMapping
    public ShoppingCart save(@RequestBody ShoppingCart shoppingCart) {
        return service.save(shoppingCart);
    }

    @RequestMapping
    public ShoppingCart get(@PathVariable Integer id) {
        // TODO
        return null;
    }

    @DeleteMapping("/empty")
    public ShoppingCart empty(ShoppingCart shoppingCart) {
        // TODO
        return null;
    }

}
