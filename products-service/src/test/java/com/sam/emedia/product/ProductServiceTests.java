package com.sam.emedia.product;


import com.sam.emedia.product.entities.Product;
import com.sam.emedia.product.entities.ProductImages;
import com.sam.emedia.product.models.ResponseObject;
import com.sam.emedia.product.repositories.ProductRepository;
import com.sam.emedia.product.services.ProductService;
import com.sam.emedia.product.urls.ObjectMapperUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
* The @Transactional annotation is used to rollback all the changes made to the database after the test is completed.
* This allows the test to run without affecting the actual data in the database.
* */
@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ProductServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    /* If we want to do not test the integration with the database, we only create a mock bean of our service

    @MockBean
    private ProductService productService;
    */

    @Autowired
    ProductRepository productRepository;

    /*
    * This test method is an integration test that test a function inside ProductController.java
    * to ensure if the response is based on MockData or if the service method is called or not
    * */
    @Test
    public void testAddProduct() throws Exception {
        ArrayList<ProductImages> productImages = new ArrayList<>();
        productImages.add(ProductImages.builder().name("prd1.jpg").build());
        productImages.add(ProductImages.builder().name("prd2.jpg").build());
        Product product = Product.builder().name("Product 13")
                .price(123)
                .productImages(productImages).build();

        /* Expected response form addProduct() method **/
        ResponseObject expectedResponse = ResponseObject.builder()
                .success(true)
                .data(product)
                .message("Product created").build();
        when(productService.addProduct(any(Product.class))).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(APPLICATION_JSON)
                .content(ObjectMapperUtils.objectToString(product)))
                .andExpect(status().isCreated());

        /*
          To verify if the addProduct method is called or not
         */
        verify(productService).addProduct(any(Product.class));
    }

    /*To test if product save to database or not */



    @Test
    public void testSaveProduct() throws Exception {
        ArrayList<ProductImages> productImages = new ArrayList<>();
        productImages.add(ProductImages.builder().name("prd1.jpg").build());
        productImages.add(ProductImages.builder().name("prd2.jpg").build());
        Product product = Product.builder().name("Product 14")
                .price(123)
                .productImages(productImages).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(APPLICATION_JSON)
                        .content(ObjectMapperUtils.objectToString(product)))
                .andExpect(status().isCreated());
        Optional<Product> savedProduct = productRepository.findByName("Product 14");
        assertTrue(savedProduct.isPresent());
        assertEquals(product.getPrice(), savedProduct.get().getPrice());
    }

}
