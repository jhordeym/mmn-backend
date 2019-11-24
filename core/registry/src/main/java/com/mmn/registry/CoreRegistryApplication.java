package com.mmn.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CoreRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreRegistryApplication.class, args);
	}

}
