package com.example.order.rest;

import com.example.order.dto.OrderDto;
import com.example.order.model.form.OrderForm;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api
public class OrderController {
    @Autowired
    private OrderDto orderDto;

    @RequestMapping(method = RequestMethod.POST, path = "/place-new-order")
    public void placeOrder(@RequestBody OrderForm orderForm) throws Exception {
        orderDto.saveOrder(orderForm);
    }
}
