package com.example.capstone_1.Controller;

import java.util.List;

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
import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.User;
import com.example.capstone_1.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping()
    public ResponseEntity getUsers(){
        if(userService.getUsers().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("List Empty!")); 
        }
        return ResponseEntity.status(200).body(userService.getUsers()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id); 
        if(user != null){
            return ResponseEntity.status(200).body(user); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found")); 
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        userService.addUser(user); 
        return ResponseEntity.status(200).body("User Added succeessfully"); 
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        
        userService.updateUser(id, user); 
        return ResponseEntity.status(200).body(new ApiResponse("User Updated succeessfully")); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        
        return ResponseEntity.status(200).body(new ApiResponse("User Deleted succeessfully")); 
    }


    @GetMapping("/{userId}/wishlist")
    public ResponseEntity getWishList(@PathVariable Integer userId){
        List<Product> wishlist = userService.getWishList(userId); 

        if(wishlist.isEmpty()){
            return ResponseEntity.status(400).body("Wish list "); 
        }

        if (wishlist != null) {
            return ResponseEntity.status(200).body(new ApiResponse("Wishlist retrieved successfully", wishlist));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("User not found"));
        }
    }


    @PostMapping("/{userId}/wishlist/add/{productId}")
    public ResponseEntity addProductToWishList(@PathVariable Integer userId, @PathVariable Integer productId){
        String result = userService.addProductToWishList(userId, productId); 

        if(result.equals("Product added to wishlist successfully")){
            return ResponseEntity.status(200).body(result);
        }else {
            return ResponseEntity.status(405).body(result); 
        }
    }

    @DeleteMapping("/{userId}/wishlist/delete/{productId}")
    public ResponseEntity removeProductFromWishList(@PathVariable Integer userId, @PathVariable Integer productId){
        String result = userService.removeProductFromWishList(userId, productId);
        if(result.equals("Product removed from wishList successfully")){
            return ResponseEntity.status(200).body(result); 
        }else {
            return ResponseEntity.status(400).body(result); 
        }
    }


    @GetMapping("/{userId}/cart")
    public ResponseEntity getCart(@PathVariable Integer userId){
        List<Product> cartList = userService.getCart(userId); 

        if(cartList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No products avaliable")); 
        }

        return ResponseEntity.status(200).body(cartList); 
    }

    @PostMapping("/{userId}/cart/{productId}")
    public ResponseEntity addProductToCart(@PathVariable Integer userId, @PathVariable Integer productId){
        String result = userService.addProductToCart(userId, productId); 

        if(result.equals("Product added to cart successfully")){
            return ResponseEntity.status(200).body(result); 
        }else{
            return ResponseEntity.status(400).body(result); 
        }
    }

    @DeleteMapping("/{userId}/cart/{productId}")
    public ResponseEntity removeFromCart(@PathVariable Integer userId, @PathVariable Integer productId){
        String result = userService.removeFromCart(userId, productId) ;

        if(result.equals("Product removed from cart successfully")){
            return ResponseEntity.status(200).body(result); 
        }else {
            return  ResponseEntity.status(400).body(result); 
        }
    }

    @PostMapping("/{userId}/{discountCode}/checkout")
    public ResponseEntity checkout(@PathVariable Integer userId, @PathVariable String discountCode){
        String result = userService.checkout(userId, discountCode); 

        if(result.equals("Checkout successful")){
            return ResponseEntity.status(200).body(result); 
        }else{
            return ResponseEntity.status(400).body(result); 
        }
    }
}
