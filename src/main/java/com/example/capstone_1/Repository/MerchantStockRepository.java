package com.example.capstone_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_1.Model.MerchantStock;

@Repository
public interface MerchantStockRepository extends JpaRepository<MerchantStock, Integer>{

    MerchantStock findByProductIdAndMerchantId(Integer productId, Integer merchantId); 
}
