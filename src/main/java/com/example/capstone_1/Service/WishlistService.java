package com.example.capstone_1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.User;
import com.example.capstone_1.Model.Wishlist;
import com.example.capstone_1.Repository.ProductRepository;
import com.example.capstone_1.Repository.UserRepository;
import com.example.capstone_1.Repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<Wishlist> getWishlistByUserId(Integer userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public Wishlist addProductToWishlist(Integer userId, Integer productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        
        if (user == null || product == null) {
            return null; // Or throw an appropriate exception
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        return wishlistRepository.save(wishlist);
    }

    public String removeProductFromWishlist(Integer userId, Integer productId) {
        Wishlist wishlist = wishlistRepository.findByUserIdAndProductId(userId, productId);
        
        if (wishlist != null) {
            wishlistRepository.delete(wishlist);
            return "Product removed from wishlist successfully";
        } else {
            return "Product not found in wishlist";
        }
    }
}
