package com.sam.emedia.product.services;

import com.sam.emedia.product.entities.Product;
import com.sam.emedia.product.entities.ProductCategory;
import com.sam.emedia.product.models.ResponseObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public ResponseObject addProduct(Product product);
    public ResponseObject geAllProducts(Pageable pageable);
    public ResponseEntity<ResponseObject> getProduct(long productId);

    public ResponseEntity<ResponseObject> deleteProduct(long productId);

    public ResponseEntity<ResponseObject> updateProduct(long productId);

    public ResponseEntity<ResponseObject> getCategoryProducts(long productId);

    public ResponseEntity<ResponseObject> createProductCategory(ProductCategory productCategory);
    public ResponseEntity<ResponseObject> getProductCategories();
    public ResponseEntity<ResponseObject> deleteProductCategory(long categoryId);
    public ResponseEntity<ResponseObject> updateProductCategory(ProductCategory productCategory);
}
