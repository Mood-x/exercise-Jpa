package com.example.capstone_1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_1.Model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{
    Wishlist findByUserIdAndProductId(Integer userId, Integer productId);
    List<Wishlist> findByUserId(Integer userId);
}
