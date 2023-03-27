package com.sam.emedia.product.repositories;

import com.sam.emedia.product.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    /**
     * We need a custom query to fetch the product category with its
     * corresponding products. Since we set the fetch property to FetchType.LAZY,
     * It will not load the products by default when calling the default method findById()
     * **/
    @Query("SELECT c FROM ProductCategory c LEFT JOIN FETCH c.products WHERE c.id = :categoryId")
    ProductCategory findProductsByProductCategoryId(@Param("categoryId") long id);
}
