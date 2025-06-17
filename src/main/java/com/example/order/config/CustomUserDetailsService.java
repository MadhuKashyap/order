package com.example.order.config;

import com.example.order.dao.UserDao;
import com.example.order.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find user by userId first, then by emailId
        UserPojo user = userDao.findByUserId(username)
                .orElseGet(() -> userDao.findByEmailId(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username)));

        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        builder.authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name())));

        return builder.build();
    }
} 