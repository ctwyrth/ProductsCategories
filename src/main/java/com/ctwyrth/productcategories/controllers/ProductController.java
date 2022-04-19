package com.ctwyrth.productcategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctwyrth.productcategories.models.Category;
import com.ctwyrth.productcategories.models.Product;
import com.ctwyrth.productcategories.services.CategoryService;
import com.ctwyrth.productcategories.services.ProductService;

@Controller
public class ProductController {
    // -----------------------variables-----------------------
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    // show all
    
    // create new
    @GetMapping("/products")
    public String index(@ModelAttribute("product") Product product) {
        return "/products/newProduct.jsp";
    }
    @PostMapping("/products")
    public String create(@Valid @ModelAttribute("product") Product product, BindingResult result) {
    	if (result.hasErrors()) {
    		return "/products/newProduct.jsp";
    	} else {
    		productService.createProduct(product);
    		return "redirect:/products";
    	}
    }

    // display one found by id
    @GetMapping("/products/{id}")
    public String showOneProductById(@PathVariable("id") Long id, Model model) {
        Product productToShow = productService.findProduct(id);
        List<Category> unusedCategories = categoryService.unusedCategories(productToShow);
        model.addAttribute("product", productToShow);
        model.addAttribute("unusedCategories", unusedCategories);
        return "/products/showProduct.jsp";
    }
    @PostMapping("/products/{id}")
    public String addCategoryToProduct(@PathVariable("id") Long id, @RequestParam("category") Long cat_id, Model model) {
    	productService.updateCategoriesInProduct(id, cat_id);
    	Product productToShow = productService.findProduct(id);
        List<Category> unusedCategories = categoryService.unusedCategories(productToShow);
        model.addAttribute("product", productToShow);
        model.addAttribute("unusedCategories", unusedCategories);
    	return "/products/showProduct.jsp";
    }
    

    // update one found by id
    @GetMapping("/products/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
    	Product productToShow = productService.findProduct(id);
    	model.addAttribute("product", productToShow);
    	return "/products/edit.jsp";
    }
    @PutMapping("/products/{id}")
    public String update(@Valid @ModelAttribute("product") Product product, BindingResult result) {
    	if (result.hasErrors()) {
        	return "/products/edit.jsp";
    	} else {
		productService.updateProduct(product);
		return "redirect:/products";
    	}
    }

    // delete one
    @DeleteMapping("/products/{id}")
    public String destroy(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}