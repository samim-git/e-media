package com.sam.emedia.product.controllers;

import com.sam.emedia.product.entities.Product;
import com.sam.emedia.product.models.ResponseObject;
import com.sam.emedia.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    final ProductService productService;
    @PostMapping()
    public ResponseEntity<ResponseObject> addProduct(@RequestBody Product product) {
        ResponseObject responseObject = productService.addProduct(product);
        if(responseObject.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseObject);
    }
}
