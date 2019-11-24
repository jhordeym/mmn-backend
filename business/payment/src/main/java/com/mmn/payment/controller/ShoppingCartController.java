package com.mmn.payment.controller;

import com.mmn.payment.model.entity.ShoppingCart;
import com.mmn.payment.service.ShoppingCartService;
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
    public ShoppingCart save(@RequestBody final ShoppingCart shoppingCart) {
        return service.save(shoppingCart);
    }

    @RequestMapping("/{id)")
    public ShoppingCart get(@PathVariable("{id}") final String id) {
        return this.service.byId(id).orElse(null);
    }

    @DeleteMapping
    public void delete(@RequestBody final ShoppingCart shoppingCart) {
        service.delete(shoppingCart);
    }

}
