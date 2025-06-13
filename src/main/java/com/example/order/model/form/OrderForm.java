package com.example.order.model.form;

import com.example.order.model.data.Address;
import com.example.order.model.enums.OrderStatus;
import com.example.order.model.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderForm {
    private String orderId;
    private Long userId;
    private List<OrderItemForm> itemList;
    private Date createdAt;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private Double totalAmount;
    private Address customerAddress;
}
