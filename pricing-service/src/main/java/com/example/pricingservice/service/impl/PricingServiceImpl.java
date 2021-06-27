package com.example.pricingservice.service.impl;

import com.example.pricingservice.dao.ProductRepository;
import com.example.pricingservice.dto.FindPriceRequest;
import com.example.pricingservice.dto.ProductDetail;
import com.example.pricingservice.dto.ProductOrder;
import com.example.pricingservice.exception.ServiceRuntimeException;
import com.example.pricingservice.model.Product;
import com.example.pricingservice.service.PricingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PricingServiceImpl implements PricingService {

    @Autowired
    private ProductRepository productRepository;

    public double calculatePrice(FindPriceRequest findPriceRequest){
        List<ProductDetail> requestedProduct = findPriceRequest.getRequestedProduct();
        Double totalPrice = requestedProduct.stream()
                .collect(Collectors.summingDouble(e -> calculatePrice(e.getId(), e.getQuantity())));

        log.info("The calculated price for the request is [{}] ", totalPrice);
        return totalPrice;
    }

    @Override
    public double calculatePrice(long productId, Integer quantity) {
        log.debug("Calculating the price for product id [{}] and quantity [{}]", productId, quantity);
        Optional<Product> product = productRepository.findById(productId);
        if ( product.isPresent()){
            long cartonPrice = product.get().getCartonPrice();
            Integer cartonCapacity = product.get().getCartonCapacity();
            Integer requestedCartonSize = quantity/cartonCapacity;
            Integer requestedProductAboveCartonSize = quantity%cartonCapacity;
            long priceOfCartonRequested = 0l;
            if (requestedCartonSize >= 3){
                priceOfCartonRequested = (long) (cartonPrice*0.9*requestedCartonSize);
            } else if (requestedCartonSize >= 1){
                priceOfCartonRequested = (long) (cartonPrice*1.0*requestedCartonSize);
            }
            double unitPrice = ((cartonPrice * 1.0) / cartonCapacity);
            double priceOfRequestedProductAboveCartonSize =
                    (requestedProductAboveCartonSize * 1.3 * unitPrice );


            double price = priceOfRequestedProductAboveCartonSize + priceOfCartonRequested;
            log.info("The calculated price for the product [{}] with quantity [{}] is [{}] ",productId,quantity, price);
            return price;
        }
        throw new ServiceRuntimeException("INVALID PRODUCT ID",
                "Product does not exist in the store");
    }

    @Override
    public List<Product> getProductList(){
        List<Product> recordsList = (List<Product>) productRepository.findAll();
        return recordsList;
    }

    public List<ProductOrder> getProductRangeList() {
        List<ProductOrder> productOrderList = new ArrayList<>();
        List<Product> recordsList = (List<Product>) productRepository.findAll();
        log.info("Get the product list for  the range of 1 to 50 with unit price");
        recordsList.stream().forEach(e -> {
            for (int i = 0; i < 51; i++) {
                ProductOrder productOrder = new ProductOrder();
                productOrder.setId(e.getId());
                productOrder.setProductName(e.getProductName());
                productOrder.setUnitPrice(calculatePrice((int) e.getId(),i)/i);
                productOrder.setOrderedQuantity(Integer.valueOf(i));
                productOrderList.add(productOrder);
            }
        });
        return productOrderList;
    }

}
