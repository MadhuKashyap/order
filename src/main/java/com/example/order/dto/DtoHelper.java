package com.example.order.dto;

import com.example.order.model.form.UserForm;
import com.example.order.pojo.UserPojo;
import org.springframework.stereotype.Component;

@Component
public class DtoHelper {
    
    public static UserPojo convertToUserPojo(UserForm userForm) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(userForm.getName());
        userPojo.setEmailId(userForm.getEmailId());
        userPojo.setMobileNumber(userForm.getMobileNumber());
        userPojo.setUserId(userForm.getUserId());
        userPojo.setUserRole(userForm.getUserRole());
        userPojo.setPassword(userForm.getPassword());
        return userPojo;
    }
} 