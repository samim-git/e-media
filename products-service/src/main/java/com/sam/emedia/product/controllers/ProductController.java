package com.sam.emedia.product.controllers;

import com.sam.emedia.product.entities.Product;
import com.sam.emedia.product.models.ResponseObject;
import com.sam.emedia.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @GetMapping("sendMsg")
    public String testMsg(){
        System.out.println("message will be sent");
        kafkaTemplate.send("msg_prdct", "Kafka message from product");
        return "Message has been sent to user";
    }
    final ProductService productService;
    @PostMapping()
    public ResponseEntity<ResponseObject> addProduct(@RequestBody Product product) {
        ResponseObject responseObject = productService.addProduct(product);
        if(responseObject.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseObject);
    }

    @GetMapping()
    public ResponseEntity<ResponseObject> getAllProducts(@RequestParam(value = "perPage", defaultValue = "5") int perPage,
                                                         @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page,perPage);
        ResponseObject responseObject = productService.geAllProducts(pageable);

        if(responseObject.isSuccess())
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
    }

    @GetMapping("addMockData")
    public void addData() {
        for(int i = 0; i< 50; i++) {
            Product product = Product.builder().name("Product"+i)
                    .price(200*(i+1)).build();
            addProduct(product);
        }
    }
}


