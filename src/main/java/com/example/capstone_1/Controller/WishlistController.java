package com.example.capstone_1.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_1.Model.Wishlist;
import com.example.capstone_1.Service.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService; 


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlistByUserId(@PathVariable Integer userId) {
        List<Wishlist> wishlist = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductToWishlist(@RequestParam Integer userId, @RequestParam Integer productId) {
        Wishlist wishlist = wishlistService.addProductToWishlist(userId, productId);
        if (wishlist != null) {
            return ResponseEntity.ok("Product added to wishlist successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to add product to wishlist");
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeProductFromWishlist(@RequestParam Integer userId, @RequestParam Integer productId) {
        String response = wishlistService.removeProductFromWishlist(userId, productId);
        return ResponseEntity.ok(response);
    }
}
