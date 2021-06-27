package com.example.pricingservice.api;

import com.example.pricingservice.dto.FindPriceRequest;
import com.example.pricingservice.dto.ProductOrder;
import com.example.pricingservice.model.Product;
import com.example.pricingservice.service.impl.PricingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PricingApi {

    @Autowired
    private PricingServiceImpl pricingService;

    @PostMapping("/calculatePrice")
    public double calculatePrice(@RequestBody FindPriceRequest findPriceRequest){
        //TODO any validation if required
        return pricingService.calculatePrice(findPriceRequest);
    }

    @GetMapping("/getProductList")
    public List<Product> getProductList(){
        return pricingService.getProductList();
    }

    @GetMapping("/getProductRangeList")
    public List<ProductOrder> getProductRangeList(){
        return pricingService.getProductRangeList();
    }

}
