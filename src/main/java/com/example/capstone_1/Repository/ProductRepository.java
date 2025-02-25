package com.example.capstone_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_1.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
