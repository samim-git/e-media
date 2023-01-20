package com.sam.emedia.product.services;

import com.sam.emedia.product.entities.Product;
import com.sam.emedia.product.models.ResponseObject;
import com.sam.emedia.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    final ProductRepository productRepository;

    @Override
    public ResponseObject addProduct(Product product) {
        return ResponseObject.builder()
                .success(true)
                .message("product added")
                .data(productRepository.save(product))
                .build();
    }

    @Override
    public ResponseObject geAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseObject getProduct(int productId) {
        return null;
    }

    @Override
    public ResponseObject deleteProduct(int productId) {
        return null;
    }

    @Override
    public ResponseObject updateProduct(int productId) {
        return null;
    }
}
