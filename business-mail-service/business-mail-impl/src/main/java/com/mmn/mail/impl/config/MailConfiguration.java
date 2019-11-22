package com.mmn.mail.impl.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Slf4j
@ComponentScan
@PropertySource("classpath:mail.properties")
public class MailConfiguration {

    @Bean
    void logConfig() {
      log.info("${spring.mail.username}");
    }
}
