package com.example.order.dto;

import com.example.order.dao.CartDao;
import com.example.order.dao.UserDao;
import com.example.order.model.form.CartForm;
import com.example.order.pojo.CartPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.order.pojo.UserPojo;

import java.util.Optional;

@Service
public class CartDto {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    public static Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public CartPojo addToCart(CartForm cartForm) throws Exception {
        Optional<UserPojo> userPojo1 = userDao.findByUserId(authentication.getName());
        Optional<UserPojo> userPojo2 = userDao.findByEmailId(authentication.getName());
        CartPojo savedToCart = new CartPojo();
        if(userPojo1.isEmpty() && userPojo2.isEmpty())
            throw new Exception("User not authenticated");
        UserPojo validUser = !userPojo1.isEmpty() ? userPojo1.get() : userPojo2.get();
        Optional<CartPojo> cartPojo = cartDao.getByUserIdAndSkuId(validUser.getUserId(), cartForm.getSkuId());
        if(cartPojo.isEmpty()) {
            savedToCart = cartDao.save(DtoHelper.getCartPojoFromForm(cartForm, validUser));
        }
        return savedToCart;
    }

    public void removeFromCart(CartForm cartForm) throws Exception {
        Optional<UserPojo> userPojo1 = userDao.findByUserId(authentication.getName());
        Optional<UserPojo> userPojo2 = userDao.findByEmailId(authentication.getName());
        if(userPojo1.isEmpty() && userPojo2.isEmpty())
            throw new Exception("User not authenticated");
        UserPojo validUser = !userPojo1.isEmpty() ? userPojo1.get() : userPojo2.get();
        Optional<CartPojo> cartPojo = cartDao.getByUserIdAndSkuId(validUser.getUserId(), cartForm.getSkuId());
        if(cartPojo.isEmpty()) {
            throw new Exception("No product available to remove from cart : " + cartForm.getSkuId());
        } else {
            cartPojo.get().setQty(cartPojo.get().getQty() - cartForm.getQty());
        }
    }
}
