package com.mmn.account.controller.payment;

import com.mmn.account.model.payment.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

    // TODO
    // @Autowired
    // private PaymentService paymentService;

    @PostMapping
    ShoppingCart saveShoppingCart(ShoppingCart paymentObject) {
        return null;
    }

    ShoppingCart paymentConfirmation(ShoppingCart paymentObject) {
        return null;
    }

    Object paymentCompletion(ShoppingCart paymentObject) {
        return null;
    }
}
