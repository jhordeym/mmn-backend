package com.mmn.account.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.payment")
public class PaymentConfig {
    String clientId;
    String clientSecret;
}
