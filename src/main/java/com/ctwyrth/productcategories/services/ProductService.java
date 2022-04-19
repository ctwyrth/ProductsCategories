package com.ctwyrth.productcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctwyrth.productcategories.models.Category;
import com.ctwyrth.productcategories.models.Product;
import com.ctwyrth.productcategories.repositories.CategoryRepository;
import com.ctwyrth.productcategories.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CategoryService categoryService;
    
    // shows all
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    // creates one
    public Product createProduct(Product p) {
        return productRepository.save(p);
    }

    // retrieves one by id
    public Product findProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            return null;
        }
    }

    // updates one
    public Product updateProduct(Product p) {
       	return productRepository.save(p);
    }
    
    // deletes one
    public void deleteProduct(Long id) {
    	productRepository.deleteById(id);
    }
    
    // retrieves associated products
    public List<Product> usedProducts(Category category) {
    	return productRepository.findAllByCategories(category);
    }
    
    // retrieves unused productss
    public List<Product> unfiledProducts(Category category) {
    	return productRepository.findByCategoriesNotContains(category);
    }
    
    // add category to product
    public Product updateCategoriesInProduct(Long id, Long category_id) {
    	// retrieve an instance of a category using another method in the service.
    	Category thisCategory = categoryService.findCategory(category_id);
    	
    	// retrieve an instance of a product using another method in the service.
    	Product thisProduct = this.findProduct(id);
    	
    	// add the product to this category's list of products
    	thisProduct.getCategories().add(thisCategory);
    	
    	// Save thisCategory, since you made changes to its product list.
    	return productRepository.save(thisProduct);	
    }
    
    // add product to category
    public Category updateProductsInCategory(Long id, Long product_id) {
    	// retrieve an instance of a category using another method in the service.
    	Category thisCategory = categoryService.findCategory(id);
    	
    	// retrieve an instance of a product using another method in the service.
    	Product thisProduct = this.findProduct(product_id);
    	
    	// add the product to this category's list of products
    	thisCategory.getProducts().add(thisProduct);
    	
    	// Save thisCategory, since you made changes to its product list.
    	return categoryRepository.save(thisCategory);	
    }
    
}