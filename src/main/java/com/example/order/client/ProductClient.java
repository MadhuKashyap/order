package com.example.order.client;

import com.example.order.model.data.InventoryData;
import com.example.order.model.data.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Arrays;

@Service
public class ProductClient {
    public static final String productBaseUrl = "http://localhost:8081/productService";
    public static final String inventory = "/inventory";
    public static final String products = "/products";

    @Autowired
    private RestTemplate restTemplate;

    public List<InventoryData> fetchInventoryById(List<String> productIds) {
        String url = productBaseUrl + inventory + "?productIds=" + String.join(",", productIds);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        InventoryData[] response = restTemplate.exchange(url, HttpMethod.GET, entity, InventoryData[].class).getBody();
        return Arrays.asList(response);
    }

    public List<ProductData> getAllProducts() {
        String url = productBaseUrl + products;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ProductData[] response = restTemplate.exchange(url, HttpMethod.GET, entity, ProductData[].class).getBody();
        return Arrays.asList(response);
    }
}
