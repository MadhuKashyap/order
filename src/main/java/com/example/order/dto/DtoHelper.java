package com.example.order.dto;

import com.example.order.model.form.CartForm;
import com.example.order.model.form.UserForm;
import com.example.order.pojo.CartPojo;
import com.example.order.pojo.UserPojo;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

    public static CartPojo getCartPojoFromForm(CartForm cartForm, UserPojo userPojo) {
        CartPojo cartPojo = new CartPojo();
        cartPojo.setQty(cartForm.getQty());
        cartPojo.setSkuId(cartForm.getSkuId());
        cartPojo.setUserId(userPojo.getUserId());
        return cartPojo;
    }
}