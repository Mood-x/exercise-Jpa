package com.example.capstone_1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Category;
import com.example.capstone_1.Repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository; 

    public List<Category> getCategories(){
        return categoryRepository.findAll(); 
    }

    public Category getCategoryById(Integer id){
        return categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id)); 
    }


    public void addCategory(Category category){
        categoryRepository.save(category); 
    }

    public String updateCategory(Integer id, Category category){
        Category  categoryId = categoryRepository.findById(id).orElse(null); 

        if(categoryId == null){
            return "Category with this id " + id + " not found"; 
            
        }else {
            categoryId.setName(category.getName());
            categoryRepository.save(categoryId); 
            return "Category with this id " + id + " updated successfully"; 
        }
    }


    public String deleteCategory(Integer id){
        Category categoryId = categoryRepository.findById(id).orElse(null); 

        if(categoryId == null){
            return "Category with this id " + id + " not found"; 
        }
        categoryRepository.deleteById(id);
        return "Category with this id " + id + " deleted successfully"; 
    }
}
