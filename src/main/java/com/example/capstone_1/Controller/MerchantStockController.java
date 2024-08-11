package com.example.capstone_1.Controller;

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
import com.example.capstone_1.Model.MerchantStock;
import com.example.capstone_1.Service.MerchantStockService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;


// ===============================================================================================

    @GetMapping
    public ResponseEntity getMerchantStocks(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks()); 
    }

// ===============================================================================================

    @GetMapping("/{id}")
    public ResponseEntity getMerchantStockById(@PathVariable Integer id){
        MerchantStock merchantStock = merchantStockService.getMerchantStockById(id);
        
        if(merchantStock != null){
            return ResponseEntity.status(200).body(merchantStock); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant stock not found")); 
    }

// ===============================================================================================

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        merchantStockService.addMerchantStock(merchantStock); 
        return ResponseEntity.status(200).body("Merchant Stock added successfully"); 
    }

// ===============================================================================================


    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @Valid @RequestBody MerchantStock merchantStock, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        String result = merchantStockService.updateMerchantStock(id, merchantStock);
        
        if(result.equals("Merchant Stock updated successfully")){
            return ResponseEntity.status(200).body(result); 
        }else {
            return ResponseEntity.status(400).body(result); 
        }
    }

// ===============================================================================================

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStocks(@PathVariable Integer id){
        String result = merchantStockService.deleteMerchantStocks(id); 
        
        if(result.equals("Merchant Stock deleted successfully")){
            return ResponseEntity.status(200).body(result); 
        }else {
            return ResponseEntity.status(400).body(result); 
        }
    }

// ===============================================================================================

    @PutMapping("/addToStock/{productId}/{merchantId}/{additionalStock}")
    public ResponseEntity addStock(@PathVariable Integer productId, @PathVariable Integer merchantId, @PathVariable int additionalStock){
        String result = merchantStockService.addStock(productId, merchantId, additionalStock); 

        if(result.equals("Added " + additionalStock +" to merchant stock")){
            return ResponseEntity.status(200).body(result); 
        }else {
            return ResponseEntity.status(404).body(result); 
        }
    }
}
