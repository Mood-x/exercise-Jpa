package com.example.capstone_1.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


// ===============================================================================================

    @GetMapping
    public ResponseEntity getProducts(){
        return ResponseEntity.status(200).body(productService.getProducts());
    }

// ===============================================================================================


    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(productService.getProductById(id)); 
    }


// ===============================================================================================


    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        String result = productService.addProduct(product);
        
        if(result.equals("Product added successfully")){
            return ResponseEntity.status(200).body(result); 
        }else {
            return ResponseEntity.status(400).body(result); 
        }

    }


// ===============================================================================================


    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        String result = productService.updateProduct(id, product); 
        
        if(result.equals("Product with this id " + id + " updated successfully")){
            return ResponseEntity.status(200).body(result); 
        }
        return ResponseEntity.status(400).body(result); 
    }

// ===============================================================================================


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        String result = productService.deleteProduct(id); 
        
        if(result.equals("Product with this id " + id + " deleted successfully")){
            return ResponseEntity.status(200).body(result); 
        }

        return ResponseEntity.status(400).body(result); 
    }
// ===============================================================================================


    @GetMapping("/filter/{rating}/{minPrice}/{maxPrice}")
    public ResponseEntity getProductsByRatingAndPriceRange(@PathVariable double rating, @PathVariable double minPrice, @PathVariable double maxPrice){
        List<Product> filteredProducts = productService.getProductsByRatingAndPriceRange(rating, minPrice, maxPrice); 
        return ResponseEntity.status(200).body(filteredProducts); 
    }

// ===============================================================================================
}
