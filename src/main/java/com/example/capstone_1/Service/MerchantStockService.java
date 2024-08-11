package com.example.capstone_1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.MerchantStock;
import com.example.capstone_1.Repository.MerchantStockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository; 

    
    public List<MerchantStock> getMerchantStocks(){
        return merchantStockRepository.findAll(); 
    }


    public MerchantStock getMerchantStockById(Integer id){
        MerchantStock merchantStock = merchantStockRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Merchant Stock not found"));

        return merchantStock; 
    }

    public MerchantStock getMerchantStockByProductAndMerchant(Integer productId, Integer merchantId){
        return merchantStockRepository.findByProductIdAndMerchantId(productId, merchantId); 
    }


    public MerchantStock addMerchantStock(MerchantStock merchantStock){
        return merchantStockRepository.save(merchantStock); 
    }



    public String updateMerchantStock(Integer id, MerchantStock merchantStockUpdated){
        MerchantStock merchantStock = merchantStockRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Merchant Stock not found")); 

        merchantStock.setProduct(merchantStockUpdated.getProduct()); 
        merchantStock.setMerchant(merchantStockUpdated.getMerchant());
        merchantStock.setStock(merchantStockUpdated.getStock());

        merchantStockRepository.save(merchantStock); 
        return "Merchant Stock updated successfully"; 
    }


    public String deleteMerchantStocks(Integer id){
        MerchantStock merchantStock = merchantStockRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Merchant Stock not found")); 

        merchantStockRepository.delete(merchantStock);
        return "Merchant Stock deleted successfully"; 
    }

    public String addStock(Integer productId, Integer merchantId, int additionalStock){
        MerchantStock stock = merchantStockRepository.findByProductIdAndMerchantId(productId, merchantId); 

        if(stock == null){
            return "Merchant Stock not found for productId: " + productId + " and merchantId: " + merchantId; 
        }
        
    stock.setStock(stock.getStock() + additionalStock);
        merchantStockRepository.save(stock); 
        
        return "Added " + additionalStock +" to merchant stock"; 
    }
}
