package com.mmn.payment.controller;

import com.mmn.payment.client.AccountClient;
import com.mmn.payment.model.entity.Payment;
import com.mmn.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final AccountClient accountClient;

    @PostMapping
    public Payment save(@RequestBody final Payment payment) {
        final Payment savedPayment = paymentRepository.save(payment);
        accountClient.updateLevelActive(
				savedPayment.getShoppingCart().getAccountId()
        );
        return savedPayment;
    }
}
