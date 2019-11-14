package com.mmn.account.controller;

import com.mmn.account.model.payment.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @GetMapping
    List<Product> getAll() {
        // TODO
        return null;
    }

}
