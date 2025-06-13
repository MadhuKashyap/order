package com.example.order.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ApplicationProperties {
    @Value("${product.service.url}")
    private String productServiceUrl;
}
