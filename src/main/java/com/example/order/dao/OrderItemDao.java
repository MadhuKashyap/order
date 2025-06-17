package com.example.order.dao;

import com.example.order.pojo.OrderItemPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItemPojo, Long> {
}