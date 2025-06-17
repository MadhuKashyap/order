package com.example.order.rest;

import com.example.order.dto.OrderDto;
import com.example.order.model.form.OrderForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Order Management", description = "APIs for managing orders")
public class OrderController {
    @Autowired
    private OrderDto orderDto;

    @Operation(summary = "Place a new order", description = "Creates a new order with the provided details")
    @RequestMapping(method = RequestMethod.POST, path = "/place-new-order")
    public void placeOrder(@RequestBody OrderForm orderForm) throws Exception {
        orderDto.saveOrder(orderForm);
    }
}
