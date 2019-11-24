package com.mmn.account.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.type.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    @Id
    private String id;
    private PaymentMethod method;
    private String currency;
}
