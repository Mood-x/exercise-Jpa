package com.example.capstone_1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Category;
import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Repository.CategoryRepository;
import com.example.capstone_1.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository; 


    public List<Product> getProducts(){
        return productRepository.findAll(); 
    }
    
    public Product getProductById(Integer id){
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id)); 
    }


    public String addProduct(Product product){
        Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);  

        if(category == null){
            return "Category ID does not exist"; 
        }
        
        product.setCategory(category);
        productRepository.save(product); 
        return "Product added successfully"; 
    }


    public String updateProduct(Integer id, Product productUpdated){
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found")); 
        
        product.setName(productUpdated.getName());
        product.setPrice(productUpdated.getPrice());
        product.setCategory(productUpdated.getCategory());
        product.setReviews(productUpdated.getReviews());
        productRepository.save(product);
        
        return "Product with this id " + id + " updated successfully"; 
    }

    public String deleteProduct(Integer id){
        Product product = productRepository.findById(id).orElse(null); 

        if(product == null){
            return "Product with this id " + id + " not found"; 
        }else {
            categoryRepository.deleteById(id);
            return "Product with this id " + id + " deleted successfully"; 
        }
    }
    

    public List<Product> getProductsByRatingAndPriceRange(double minRating, double minPrice, double maxPrice){
        List<Product> filteredProducts = new ArrayList<>();
        List<Product> products = productRepository.findAll(); 

        for(Product product : products){
            double averageRating = product.getAverageRating(); 
            double price = product.getPrice(); 

            if(averageRating >= minRating && price >= minPrice && price <= maxPrice){
                filteredProducts.add(product); 
            }
        }

        return filteredProducts; 
    }
}
