package com.mmn.account.controller;

import com.mmn.account.model.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/shopping-cart")
@Slf4j
public class ShoppingCartController {

    @PostMapping
    public ShoppingCart save(@RequestBody ShoppingCart shoppingCart) {
        // TODO

        return null;
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
