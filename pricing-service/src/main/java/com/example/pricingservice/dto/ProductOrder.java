package com.example.pricingservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrder {
    private long id;
    private String ProductName;
    private Integer orderedQuantity;
    private double unitPrice;
}
