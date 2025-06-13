package com.example.order.model.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemForm {
    private String skuId;
    private Integer qty;
    private Double sellingPrice;
    private Double discount;
    private Double totalPrice;

}
