package com.mmn.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmn.account.model.ShoppingCart;

@RestController
@CrossOrigin("*")
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

    // TODO
    // @Autowired
    // private PaymentService paymentService;

    @PostMapping
    Object paymentCreation(ShoppingCart paymentObject) {
        return null;
    }

    Object paymentConfirmation(ShoppingCart paymentObject) {
        return null;
    }

    Object paymentCompletion(ShoppingCart paymentObject) {
        return null;
    }
}
