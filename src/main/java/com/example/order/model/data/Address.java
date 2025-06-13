package com.example.order.model.data;

import com.example.order.model.enums.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private AddressType addressType;
}
