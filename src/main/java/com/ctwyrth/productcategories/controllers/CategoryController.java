package com.ctwyrth.productcategories.controllers;

import java.util.List;

// import java.util.List;

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
public class CategoryController {
    // -----------------------variables-----------------------
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProductService productService;

    // show all 
    
    // create new
    @GetMapping("/categories")
    public String index(@ModelAttribute("category") Category category) {
        return "/categories/newCategory.jsp";
    }
    @PostMapping("/categories")
    public String create(@Valid @ModelAttribute("category") Category category, BindingResult result) {
    	if (result.hasErrors()) {
    		return "/categories/index.jsp";
    	} else {
    		categoryService.createCategory(category);
    		return "redirect:/categories";
    	}
    }

 // display one found by id
    @GetMapping("/categories/{id}")
    public String showOneCategoryById(@PathVariable("id") Long id, Model model) {
        Category categoryToShow = categoryService.findCategory(id);
        List<Product> unfiledProducts = productService.unfiledProducts(categoryToShow);
        model.addAttribute("category", categoryToShow);
        model.addAttribute("unfiledProducts", unfiledProducts);
        return "/categories/showCategory.jsp";
    }
    @PostMapping("/categories/{id}")
    public String addProductToCategory(@PathVariable("id") Long id, @RequestParam("product") Long prod_id, Model model) {
    	productService.updateProductsInCategory(id, prod_id);
    	Category categoryToShow = categoryService.findCategory(id);
        List<Product> unfiledProducts = productService.unfiledProducts(categoryToShow);
        model.addAttribute("category", categoryToShow);
        model.addAttribute("unfiledProducts", unfiledProducts);
    	return "/categories/showCategory.jsp";
    }
    

    // update one found by id
    @GetMapping("/categories/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
    	Category categoryToShow = categoryService.findCategory(id);
	model.addAttribute("category", categoryToShow);
	return "/categories/edit.jsp";
    }
    @PutMapping("/categories/{id}")
    public String update(@Valid @ModelAttribute("category") Category category, BindingResult result) {
	if (result.hasErrors()) {
        	return "/categories/edit.jsp";
	} else {
		categoryService.updateCategory(category);
		return "redirect:/categories";
	}
    }

    // delete one
    @DeleteMapping("/categories/{id}")
    public String destroy(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
	return "redirect:/categories";
    }
}