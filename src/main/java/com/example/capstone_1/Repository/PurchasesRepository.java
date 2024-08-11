package com.example.capstone_1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_1.Model.Purchases;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, Integer>{
    List<Purchases> findByUserId(Integer userId);
}
