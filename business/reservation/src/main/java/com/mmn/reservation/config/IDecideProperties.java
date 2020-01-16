package com.mmn.reservation.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@PropertySource("classpath:idecide.properties")
@ConfigurationProperties(prefix = "idecide")
public class IDecideProperties {
    private String url;
    private String apiUser;
    private String apiKey;
}
