package com.mmn.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.type.ShoppingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Account account;
    @Embedded
    private Address alternativeAddress;
    @OneToMany(targetEntity = CartProduct.class, mappedBy = "shoppingCart")
    private List<CartProduct> products;
    private LocalDate creationDate;
    @ManyToOne
    private Payment payment;
    private ShoppingType shoppingType;

}
