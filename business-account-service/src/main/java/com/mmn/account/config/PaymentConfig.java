package com.mmn.account.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class PaymentConfig {
    @Value("app.payment.clientId")
    String clientID;
    @Value("app.payment.clientSecret")
    String clientSecret;
}
