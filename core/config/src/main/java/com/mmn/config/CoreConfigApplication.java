package com.mmn.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
@EnableScheduling
public class CoreConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreConfigApplication.class, args);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60) // every hour
    public void doNothing() {
        // Forces Spring Scheduling managing thread to start
    }

    @RestController
    class ConfigController {

        @GetMapping("/hello")
        public String hello() {
            return "hello";
        }
    }
}
