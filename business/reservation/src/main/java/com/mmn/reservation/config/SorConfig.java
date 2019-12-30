package com.mmn.reservation.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SorConfig {
    @Value("${SOR_URL}")
    private String SOR_URL;

    @Bean
    public void init() {
        log.info(SOR_URL);
    }
}
