package com.ctwyrth.productcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctwyrth.productcategories.models.Category;
import com.ctwyrth.productcategories.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    // this method retrieves all from the database
    List<Product> findAll();

    // retrieves a list of all categories for a product
    List<Product> findAllByCategories(Category category);
    
    // retrieves a list of all categories not associated to product
    List<Product> findByCategoriesNotContains(Category category);
}