package com.example.capstone_1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Merchant;
import com.example.capstone_1.Repository.MerchantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository; 


    public List<Merchant> getMerchants(){
        return merchantRepository.findAll(); 
    }

    public Merchant getMerchantById(Integer id){
        return merchantRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Merchant not found")); 
    }


    public Merchant addMerchant(Merchant merchant){
        return merchantRepository.save(merchant); 
    }



    public Merchant updateMerchant(Integer id, Merchant merchantUpdated){
        Merchant merchant = merchantRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Merchant not found")); 

        merchant.setName(merchantUpdated.getName());
        return merchantRepository.save(merchant); 
    }


    public String deleteMerchant(Integer id){
        Merchant merchant = merchantRepository.findById(id).orElse(null); 

        if(merchant == null){
            return "Merchant whth this id " + id + " not found"; 
        }
        merchantRepository.deleteById(id);
        return "Merchant deleted successfully"; 
    }
}
