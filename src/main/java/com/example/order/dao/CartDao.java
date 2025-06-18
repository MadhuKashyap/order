package com.example.order.dao;

import com.example.order.pojo.CartPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartDao extends JpaRepository<CartPojo, Long> {
    Optional<CartPojo> getByUserIdAndSkuId(String userId, String skuId);
}
