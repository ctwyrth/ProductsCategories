package com.ctwyrth.productcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctwyrth.productcategories.models.Category;
import com.ctwyrth.productcategories.models.Product;
import com.ctwyrth.productcategories.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    
    // shows all
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    // creates one
    public Category createCategory(Category c) {
        return categoryRepository.save(c);
    }

    // retrieves one by id
    public Category findCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            return null;
        }
    }

    // updates one
    public Category updateCategory(Category c) {
       	return categoryRepository.save(c);
    }
    
    // deletes one
    public void deleteCategory(Long id) {
    	categoryRepository.deleteById(id);
    }
    
    // retrieves associated categories
    public List<Category> usedCategories(Product product) {
    	return categoryRepository.findAllByProducts(product);
    }
    
    // retrieves unused categories
    public List<Category> unusedCategories(Product product) {
    	return categoryRepository.findByProductsNotContains(product);
    }
    

}