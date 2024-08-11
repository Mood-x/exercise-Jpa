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

import com.example.capstone_1.Model.Cart;
import com.example.capstone_1.Service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private CartService cartService; 



    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable Integer userId) {
        List<Cart> cartItems = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(@RequestParam Integer userId, @RequestParam Integer productId) {
        Cart cart = cartService.addProductToCart(userId, productId);
        if (cart != null) {
            return ResponseEntity.ok("Product added to cart successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to add product to cart");
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeProductFromCart(@RequestParam Integer userId, @RequestParam Integer productId) {
        String response = cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(response);
    }
}
