package com.sam.emedia.product.services;

import com.sam.emedia.product.entities.Product;
import com.sam.emedia.product.models.ResponseObject;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public interface ProductService {
    public ResponseObject addProduct(Product product);
    public ResponseObject geAllProducts(Pageable pageable);
    public ResponseObject getProduct(int productId);

    public ResponseObject deleteProduct(int productId);

    public ResponseObject updateProduct(int productId);
}
