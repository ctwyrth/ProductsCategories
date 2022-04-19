package com.ctwyrth.productcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctwyrth.productcategories.models.Category;
import com.ctwyrth.productcategories.models.Product;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    // this method retrieves all from the database
    List<Category> findAll();

    // retrieves a list of all categories for a product
    List<Category> findAllByProducts(Product product);
    
    // retrieves a list of all categories not associated to product
    List<Category> findByProductsNotContains(Product product);
    
}