package com.example.order.pojo;

import com.example.order.model.enums.OrderStatus;
import com.example.order.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(nullable = false)
    private Long userId;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod = PaymentMethod.COD;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Double totalAmount;
    private Long addressId;
}
