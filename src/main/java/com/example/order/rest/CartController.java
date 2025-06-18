package com.example.order.rest;

import com.example.order.dto.CartDto;
import com.example.order.model.form.CartForm;
import com.example.order.pojo.CartPojo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Cart Management", description = "APIs for managing carts")
public class CartController {

    @Autowired
    private CartDto cartDto;

    @Operation(summary = "Adds product to cart")
    @RequestMapping(method = RequestMethod.POST, path = "/add-to-cart")
    public CartPojo addToCart(@RequestBody CartForm cartForm) throws Exception {
        return cartDto.addToCart(cartForm);
    }
    @Operation(summary = "Removes product from cart")
    @RequestMapping(method = RequestMethod.POST, path = "/remove-from-cart")
    public void deleteFromCart(@RequestBody CartForm cartForm) throws Exception {
        cartDto.removeFromCart(cartForm);
    }
}
