package com.example.pricingservice.service;

import com.example.pricingservice.dto.FindPriceRequest;
import com.example.pricingservice.model.Product;

import java.util.List;


public interface PricingService {
    double calculatePrice (FindPriceRequest request);
    double calculatePrice (long productId, Integer quantity);

    List<Product> getProductList();
}
