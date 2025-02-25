package com.example.DevStore;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
@EnableFeignClients
public class DevStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevStoreApplication.class, args);
	}

}
