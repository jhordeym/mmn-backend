package com.mmn.account.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.type.ShoppingType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Account account;
    @Embedded
    private Address alternativeAddress;
    @OneToMany(targetEntity=CartProduct.class, mappedBy="shoppingCart")
    private List<CartProduct> products;
    private LocalDate creationDate;
    @ManyToOne
    private Payment payment;
	private ShoppingType shoppingType;

}
