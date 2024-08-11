package com.example.capstone_1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Cart;
import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.User;
import com.example.capstone_1.Repository.CartRepository;
import com.example.capstone_1.Repository.ProductRepository;
import com.example.capstone_1.Repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<Cart> getCartByUserId(Integer userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addProductToCart(Integer userId, Integer productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        
        if (user == null || product == null) {
            return null; 
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        return cartRepository.save(cart);
    }

    public String removeProductFromCart(Integer userId, Integer productId) {
        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
        
        if (cart != null) {
            cartRepository.delete(cart);
            return "Product removed from cart successfully";
        } else {
            return "Product not found in cart";
        }
    }
}
