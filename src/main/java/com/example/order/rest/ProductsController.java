package com.example.order.rest;

import com.example.order.dto.OrderDto;
import com.example.order.model.data.ProductData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api
public class ProductsController {
    @Autowired
    private OrderDto dto;

    @RequestMapping(method = RequestMethod.GET, path = "/get-products")
    public List<ProductData> getAllProducts() throws Exception {
        return dto.getAllProducts();
    }

}
