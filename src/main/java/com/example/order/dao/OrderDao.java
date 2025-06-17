package com.example.order.dao;

import com.example.order.pojo.OrderPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrderPojo, Long> {
}
