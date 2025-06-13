package com.example.order.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orderItem")
public class OrderItemPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String skuId;
    private Long orderId;
    private Integer qty;
    private Double sellingPrice;
    private Double discount;
    private Double totalPrice;
}
