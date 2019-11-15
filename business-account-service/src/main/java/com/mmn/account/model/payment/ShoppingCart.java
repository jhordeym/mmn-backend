package com.mmn.account.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.model.Account;
import com.mmn.account.model.Address;
import com.mmn.account.type.PaymentType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCart {
    @Id
    private Integer id;
    private Account account;
    private Address alternativeAddress;
    private List<Product> products;
    private LocalDate creationDate;
    private PaymentType paymentType;
}
