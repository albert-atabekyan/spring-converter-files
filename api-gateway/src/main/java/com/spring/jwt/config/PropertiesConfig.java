package com.spring.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:microservice.properties")
public class PropertiesConfig {
}
