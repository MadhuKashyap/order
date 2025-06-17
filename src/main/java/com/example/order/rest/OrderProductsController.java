package com.example.order.rest;

import com.example.order.dto.OrderDto;
import com.example.order.model.data.ProductData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Order Product Management", description = "APIs for managing products in orders")
public class OrderProductsController {
    @Autowired
    private OrderDto dto;

    @Operation(summary = "Get all products", description = "Retrieves a list of all available products")
    @RequestMapping(method = RequestMethod.GET, path = "/get-products")
    public List<ProductData> getAllProducts() throws Exception {
        return dto.getAllProducts();
    }
}
