package com.example.pricingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FindPriceRequest {
    List<ProductDetail> requestedProduct = new ArrayList<>();


}
