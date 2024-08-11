package com.example.capstone_1.Controller;

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

import com.example.capstone_1.API.ApiResponse;
import com.example.capstone_1.Model.Category;
import com.example.capstone_1.Service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService; 

// ===============================================================================================

    @GetMapping
    public ResponseEntity getCategories(){
        return ResponseEntity.status(200).body(categoryService.getCategories()); 
    }
    
// ===============================================================================================


    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(categoryService.getCategoryById(id)); 
    }

// ===============================================================================================

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category added successfully"); 
    }

// ===============================================================================================


    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        String result = categoryService.updateCategory(id, category); 

        
        if(result.equals("Category with this id " + id + " updated successfully")){
            return ResponseEntity.status(200).body(new ApiResponse(result)); 
        }else {
            return ResponseEntity.status(400).body(new ApiResponse(result)); 
        }
    }

// ===============================================================================================


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        String result = categoryService.deleteCategory(id); 

        if(result.equals("Category with this id " + id + " deleted successfully")){
            return ResponseEntity.status(200).body(new ApiResponse(result)); 
        }else {
            return ResponseEntity.status(400).body(new ApiResponse(result)); 
        }
    }

// ===============================================================================================

}
