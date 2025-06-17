package com.example.order.model.form;

import com.example.order.model.enums.ROLE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    private String name;
    private Long mobileNumber;
    private String emailId;
    private String password;
    private String userId;
    private ROLE userRole;
} 