package com.mmn.account.model.payment;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Product {
    @Id
            // TODO: generate id;
    Long id;
    String name;
    String description;
    String price;
    String priceTC;
}
