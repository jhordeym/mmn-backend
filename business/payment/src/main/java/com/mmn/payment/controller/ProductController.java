package com.mmn.payment.controller;

import com.mmn.payment.model.entity.Product;
import com.mmn.payment.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable("/{id}") final String id) {
        // comment here
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Product save(final Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping
    public void delete(final Product product) {
        productRepository.delete(product);
    }

}
