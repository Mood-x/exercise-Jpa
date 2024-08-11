package com.example.capstone_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_1.Model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer>{

}
