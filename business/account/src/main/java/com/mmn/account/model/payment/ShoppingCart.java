package com.mmn.account.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mmn.account.model.Address;
import com.mmn.account.type.ShoppingCartStatus;
import com.mmn.account.type.ShoppingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountId;
    @Embedded
    private Address alternativeAddress;
    @OneToMany(targetEntity = CartProduct.class, mappedBy = "shoppingCart")
    @JsonIgnoreProperties("shoppingCart")
    private List<CartProduct> products;
    private LocalDate creationDate;
    @ManyToOne
    private Payment payment;
    private ShoppingType shoppingType;
    private ShoppingCartStatus shoppingCartStatus;

    @JsonProperty("productDescription")
    public String getProductDescription() {
       try {
           return products.stream().map(p -> p.getProduct().getDescription()).collect(Collectors.joining(","));
       } catch(Exception e) {
           return null;
       }
    }

}
