package com.example.order.client;

import com.example.order.exception.ProductServiceException;
import com.example.order.model.data.InventoryData;
import com.example.order.model.data.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class ClientWrapper {
    @Autowired
    private ProductClient client;

    private void handleException(Exception e, String operation) {
        if (e instanceof HttpClientErrorException) {
            throw new ProductServiceException("Failed to " + operation + ": " + e.getMessage(), (HttpStatus) ((HttpClientErrorException) e).getStatusCode());
        } else if (e instanceof HttpServerErrorException) {
            throw new ProductServiceException("Product service is currently unavailable. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
        } else if (e instanceof RestClientException) {
            throw new ProductServiceException("Unable to connect to product service. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            throw new ProductServiceException("An unexpected error occurred while " + operation + ".", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<InventoryData> fetchInventoryById(List<String> productIds) {
        try {
            return client.fetchInventoryById(productIds);
        } catch (Exception e) {
            handleException(e, "fetch inventory data");
            return null;
        }
    }

    public List<ProductData> getAllProducts() {
        try {
            return client.getAllProducts();
        } catch (Exception e) {
            handleException(e, "fetch products");
            return null; // This line will never be reached due to the exception
        }
    }
}
