package com.sam.emedia.product.services;

import com.sam.emedia.product.entities.Product;
import com.sam.emedia.product.entities.ProductCategory;
import com.sam.emedia.product.models.ResponseObject;
import com.sam.emedia.product.repositories.ProductCategoryRepository;
import com.sam.emedia.product.repositories.ProductRepository;
import com.sam.emedia.product.urls.ResponseUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{
    final ProductRepository productRepository;
    final ProductCategoryRepository productCategoryRepository;

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
        return ResponseObject.builder().success(true).data(productRepository.findAll(pageable).get()).build();
    }

    @Override
    public ResponseEntity<ResponseObject> getProduct(long productId) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseObject> deleteProduct(long productId) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseObject> updateProduct(long productId) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseObject> getCategoryProducts(long productId) {
        try {
            return ResponseUtils.createSuccess(productCategoryRepository.findProductsByProductCategoryId(productId));
        } catch (Exception e) {
            return ResponseUtils.internalServerError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ResponseObject> createProductCategory(ProductCategory productCategory) {
        try {
            return ResponseUtils.createSuccess(productCategoryRepository.save(productCategory));
        } catch (Exception e) {
            return ResponseUtils.internalServerError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getProductCategories() {
        try {
            return ResponseUtils.getSuccess(productCategoryRepository.findAll());
        } catch (Exception e) {
            return ResponseUtils.internalServerError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ResponseObject> deleteProductCategory(long categoryId) {
        try {
            productCategoryRepository.deleteById(categoryId);
            return ResponseUtils.successResponse(null, "Category successfully deleted", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseUtils.failureResponse(null, "Record not found", HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<ResponseObject> updateProductCategory(ProductCategory productCategory) {
        Optional<ProductCategory> categoryToUpdate = productCategoryRepository.findById(productCategory.getId());
        if(!categoryToUpdate.isPresent()) {
            return ResponseUtils.badRequest("Cannot find the entity");
        }
        ProductCategory updatableCategory = categoryToUpdate.get();
        if(productCategory.getName() != null)
            updatableCategory.setName(productCategory.getName());
        if(productCategory.getPrice() != 0)
            updatableCategory.setPrice(productCategory.getPrice());
//        updatableCategory.setUpdated(Instant.now());
        return ResponseUtils.createSuccess(productCategoryRepository.save(updatableCategory));
    }

}
