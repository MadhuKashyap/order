package com.example.order.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "skuId"})})
public class CartPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String skuId;
    private Long orderId;
    private int qty;
}
