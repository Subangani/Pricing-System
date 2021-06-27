package com.example.pricingservice.service;

import com.example.pricingservice.dao.ProductRepository;
import com.example.pricingservice.dto.FindPriceRequest;
import com.example.pricingservice.dto.ProductDetail;
import com.example.pricingservice.model.Product;
import com.example.pricingservice.service.impl.PricingServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PricingServiceImplTest {

    @InjectMocks
    private PricingServiceImpl pricingService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(pricingService, "productRepository",productRepository);
    }

    @Test
    public void testCalculatePriceLessThanCartonSize(){
        Product penguinEars = new Product();
        penguinEars.setCartonCapacity(20);
        penguinEars.setCartonPrice(175);
        penguinEars.setId(1);
        penguinEars.setProductName("Penguin-ears");

        Mockito.when(productRepository.findById((long) 1)).thenReturn(java.util.Optional.of(penguinEars));
        double calculatedPrice = pricingService.calculatePrice(1, 12);
        double actualPrice = 136.5;
        Assert.assertTrue(calculatedPrice == actualPrice);
    }

    @Test
    public void testCalculatePriceLessThan3Carton(){
        Product penguinEars = new Product();
        penguinEars.setCartonCapacity(20);
        penguinEars.setCartonPrice(175);
        penguinEars.setId(1);
        penguinEars.setProductName("Penguin-ears");

        Mockito.when(productRepository.findById((long) 1)).thenReturn(java.util.Optional.of(penguinEars));
        double calculatedPrice = pricingService.calculatePrice(1, 55);
        double actualPrice = 170.625 + 175*2;
        Assert.assertTrue(calculatedPrice == actualPrice);
    }

    @Test
    public void testCalculatePriceGreaterThan3Carton(){
        Product penguinEars = new Product();
        penguinEars.setCartonCapacity(20);
        penguinEars.setCartonPrice(175);
        penguinEars.setId(1);
        penguinEars.setProductName("Penguin-ears");

        Mockito.when(productRepository.findById((long) 1)).thenReturn(java.util.Optional.of(penguinEars));
        double calculatedPrice = pricingService.calculatePrice(1, 84);
        double actualPrice = 45.5+ (175*4*0.9);
        Assert.assertTrue(calculatedPrice==actualPrice);
    }

    @Test
    public void testCalculatedRequestedProductPrice(){
        FindPriceRequest findPriceRequest = new FindPriceRequest();
        List<ProductDetail> productDetails = new ArrayList<>();
        ProductDetail product1 = new ProductDetail();
        product1.setId(1);
        product1.setQuantity(20);
        ProductDetail product2 = new ProductDetail();
        product2.setId(2);
        product2.setQuantity(5);
        productDetails.add(product1);
        productDetails.add(product2);
        findPriceRequest.setRequestedProduct(productDetails);

        Product penguinEars = new Product();
        penguinEars.setCartonCapacity(20);
        penguinEars.setCartonPrice(175);
        penguinEars.setId(1);
        penguinEars.setProductName("Penguin-ears");

        Product horseshoe = new Product();
        horseshoe.setCartonCapacity(5);
        horseshoe.setCartonPrice(825);
        horseshoe.setId(2);
        horseshoe.setProductName("Horseshoe");

        Mockito.when(productRepository.findById((long) 1)).thenReturn(java.util.Optional.of(penguinEars));
        Mockito.when(productRepository.findById((long) 2)).thenReturn(java.util.Optional.of(horseshoe));
        double calculatedPrice = pricingService.calculatePrice(findPriceRequest);
        double actualPrice = 175 + 825;
        Assert.assertTrue(calculatedPrice==actualPrice);
    }


    @Test
    public void testCalculatedRequestedMultipleProductPrice(){
        FindPriceRequest findPriceRequest = new FindPriceRequest();
        List<ProductDetail> productDetails = new ArrayList<>();
        ProductDetail product1 = new ProductDetail();
        product1.setId(1);
        product1.setQuantity(84);
        ProductDetail product2 = new ProductDetail();
        product2.setId(2);
        product2.setQuantity(5);
        productDetails.add(product1);
        productDetails.add(product2);
        findPriceRequest.setRequestedProduct(productDetails);

        Product penguinEars = new Product();
        penguinEars.setCartonCapacity(20);
        penguinEars.setCartonPrice(175);
        penguinEars.setId(1);
        penguinEars.setProductName("Penguin-ears");

        Product horseshoe = new Product();
        horseshoe.setCartonCapacity(5);
        horseshoe.setCartonPrice(825);
        horseshoe.setId(2);
        horseshoe.setProductName("Horseshoe");

        Mockito.when(productRepository.findById((long) 1)).thenReturn(java.util.Optional.of(penguinEars));
        Mockito.when(productRepository.findById((long) 2)).thenReturn(java.util.Optional.of(horseshoe));
        double calculatedPrice = pricingService.calculatePrice(findPriceRequest);
        double actualPrice = 45.5+ (175*4*0.9) + 825;
        Assert.assertTrue(calculatedPrice==actualPrice);
    }
}
