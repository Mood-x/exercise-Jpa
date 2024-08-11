package com.example.capstone_1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_1.Model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
    List<Review> findByProductid(Integer productId); 
    List<Review> findByUserId(Integer userId); 

}
