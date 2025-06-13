package com.example.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.order.dao")
@EnableTransactionManagement
@EntityScan(basePackages = "com.example.order.pojo")
@ComponentScan(basePackages = "com.example.order")
public class DatabaseConfig {
    // Additional database configurations can be added here if needed
} 