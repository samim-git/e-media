package com.sam.emedia.product.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productCategories", schema = "eMedia_products_db", catalog = "")
public class ProductCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "created")
    Instant created = Instant.now();

    @Column(name = "updated")
    Instant updated = Instant.now();
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price = 0;

    /**
     * mappedBy: This attribute is used to specify the name of the field on the child entity
     *          (Product) that maps the relationship back to the parent entity (ProductCategory).
     *          In this case, the Product entity has a field named productCategory that maps the
     *          relationship back to the ProductCategory entity. By setting
     *          mappedBy = "productCategory", we are telling JPA to use this field to map
     *          the relationship.
     *
     * cascade: This attribute is used to specify the cascade operations that should be applied to
     *         the child entity (Product) when a corresponding operation is applied to the parent
     *         entity (ProductCategory). In this case, we are specifying CascadeType.ALL, which
     *         means that any operation (e.g., persist, merge, remove, etc.) applied to a
     *         ProductCategory entity should be cascaded to its products collection.
     *
     * orphanRemoval: This attribute is used to specify whether orphaned child entities
     *          (i.e., child entities that are no longer associated with a parent entity) should be
     *          automatically removed when a parent entity is removed. In this case, we are setting
     *          orphanRemoval = true, which means that any Product entity that is removed from
     *          a ProductCategory's products collection should be automatically removed from the database.
     * **/
    @OneToMany(
            mappedBy = "productCategory",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<ProductCategoryImages> productCategoryImages;

    @OneToMany(
            mappedBy = "productCategory",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    List<Product> products;

    /**
     *
     * **/
    @PreUpdate
    private void beforeUpdate(){
        this.setUpdated(Instant.now());
    }
}

