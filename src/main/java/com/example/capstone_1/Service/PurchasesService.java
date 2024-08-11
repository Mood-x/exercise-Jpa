package com.example.capstone_1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Purchases;
import com.example.capstone_1.Model.User;
import com.example.capstone_1.Repository.PurchasesRepository;
import com.example.capstone_1.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchasesService {


    private final PurchasesRepository purchasesRepository;
    private final UserRepository userRepository;

    public List<Purchases> getPurchasesByUserId(Integer userId) {
        return purchasesRepository.findByUserId(userId);
    }

    public Purchases addPurchase(Purchases purchase) {
        User user = userRepository.findById(purchase.getUser().getId()).orElse(null);
        
        if (user == null) {
            return null;
        }

        return purchasesRepository.save(purchase);
    }
}
