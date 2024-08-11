package com.example.capstone_1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.Review;
import com.example.capstone_1.Model.User;
import com.example.capstone_1.Repository.ProductRepository;
import com.example.capstone_1.Repository.ReviewRepository;
import com.example.capstone_1.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository; 
    private final UserRepository userRepository; 
    private final ProductRepository productRepository; 


    public Review addReview(Integer userId, Integer productId, double rating, String comment){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found")); 
            
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found")); 

        Review review = new Review(); 
        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review); 
    }


    public List<Review> getReviewsByProduct(Integer productId) {
        return reviewRepository.findByProductid(productId);
    }

    public List<Review> getReviewsByUser(Integer userId) {
        return reviewRepository.findByUserId(userId);
    }


    public String deleteReview(Integer reviewId){
        Review review = reviewRepository.findById(reviewId).orElse(null); 

        if(review == null){
            return "Review with this id " + reviewId + " not found"; 
        }
        reviewRepository.deleteById(reviewId);

        return "Review deleted successfully"; 
    }
}
