package com.mmn.reservation.config;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SorConfig {
    @Autowired
    private SorProperties properties;

    @Bean
    public void init() {
        log.info(properties.getSOR_URL());
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
