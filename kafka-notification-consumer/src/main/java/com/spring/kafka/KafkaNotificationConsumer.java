package com.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaNotificationConsumer {

	public static void main(String[] args) {
		SpringApplication.run(KafkaNotificationConsumer.class, args);
	}

}
