package com.example.capstone_1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_1.Model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
    Cart findByUserIdAndProductId(Integer userId, Integer productId);
    List<Cart> findByUserId(Integer userId);
}
