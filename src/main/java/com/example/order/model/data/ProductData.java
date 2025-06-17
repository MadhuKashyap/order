package com.example.order.model.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductData {
    private String skuId;
    private String name;
    private Double price;
    private String category;
    private int quantity;
    private String image;
}
