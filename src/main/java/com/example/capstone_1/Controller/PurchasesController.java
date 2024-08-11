package com.example.capstone_1.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_1.Model.Purchases;
import com.example.capstone_1.Service.PurchasesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
public class PurchasesController {
    private final PurchasesService purchasesService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Purchases>> getPurchasesByUserId(@PathVariable Integer userId) {
        List<Purchases> purchases = purchasesService.getPurchasesByUserId(userId);
        return ResponseEntity.ok(purchases);
    }

    @PostMapping("/add")
    public ResponseEntity<Purchases> addPurchase(@RequestBody Purchases purchase) {
        Purchases newPurchase = purchasesService.addPurchase(purchase);
        return ResponseEntity.ok(newPurchase);
    }
}
