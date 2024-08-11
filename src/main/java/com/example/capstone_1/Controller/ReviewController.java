package com.example.capstone_1.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_1.Model.Review;
import com.example.capstone_1.Service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    
    @GetMapping("/product/{productId}")
    public ResponseEntity getReviewsByProduct(@PathVariable Integer productId){
        List<Review> review = reviewService.getReviewsByProduct(productId);
        return ResponseEntity.status(200).body(review); 
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity addReview(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable double rating, @PathVariable(required = false) String comment){
        Review review = reviewService.addReview(userId, productId, rating, comment);
        return ResponseEntity.status(200).body(review); 
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getReviewsByUser(@PathVariable Integer userId){
        List<Review> review = reviewService.getReviewsByUser(userId);
        return ResponseEntity.status(200).body(review); 
    }


    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Integer reviewId){
        String result = reviewService.deleteReview(reviewId); 

        if(result.equals("Review deleted successfully")){
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(200).body(result);
    }
}
