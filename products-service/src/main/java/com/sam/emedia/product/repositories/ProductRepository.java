package com.sam.emedia.product.repositories;

import com.sam.emedia.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(int id);
    Optional<Product> findByName(String name);
}
