package com.example.order.rest;

import com.example.order.dto.UserDto;
import com.example.order.model.form.UserForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {
    
    @Autowired
    private UserDto userDto;

    @PostMapping("/add-user")
    @Operation(summary = "Add a new user", description = "Creates a new user with the provided details")
    public void addUser(@RequestBody UserForm userForm) throws Exception {
        userDto.saveUser(userForm);
    }
}
