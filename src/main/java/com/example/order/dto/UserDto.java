package com.example.order.dto;

import com.example.order.dao.UserDao;
import com.example.order.model.form.UserForm;
import com.example.order.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDto {
    @Autowired
    private UserDao userDao;


    public UserPojo saveUser(UserForm userForm) throws Exception {
        // Check if user already exists with the same email
        if (userDao.findByEmailId(userForm.getEmailId()).isPresent()) {
            throw new Exception("User already exists with email: " + userForm.getEmailId());
        }

        // Check if user already exists with the same userId
        if (userDao.findByUserId(userForm.getUserId()).isPresent()) {
            throw new Exception("User already exists with userId: " + userForm.getUserId());
        }

        // Convert form to pojo
        UserPojo userPojo = new UserPojo();
        userPojo.setName(userForm.getName());
        userPojo.setEmailId(userForm.getEmailId());
        userPojo.setMobileNumber(userForm.getMobileNumber());
        userPojo.setUserId(userForm.getUserId());
        userPojo.setUserRole(userForm.getUserRole());
        userPojo.setPassword(userForm.getPassword());
        // Save user
        return userDao.save(userPojo);
    }
} 