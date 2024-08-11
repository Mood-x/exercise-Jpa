package com.example.capstone_1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.MerchantStock;
import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.User;
import com.example.capstone_1.Model.Wishlist;
import com.example.capstone_1.Repository.CartRepository;
import com.example.capstone_1.Repository.ProductRepository;
import com.example.capstone_1.Repository.PurchasesRepository;
import com.example.capstone_1.Repository.UserRepository;
import com.example.capstone_1.Repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantStockService merchantStockService;
    private final PurchasesRepository purchasesRepository; 
    private final WishlistRepository wishlistRepository; 
    private final ProductRepository productRepository; 
    private final CartRepository cartRepository; 
    private final UserRepository userRepository; 

    private double applyDiscount(double price, double discountPercentage) {
        return price - (price * (discountPercentage / 100));
    }


    public List<User> getUsers(){
        return userRepository.findAll(); 
    }


    public User addUser(User user){
        return userRepository.save(user); 
    }

    public User getUserById(Integer id){
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found")); 
    }

    public User updateUser(Integer id, User userUpdated){
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found")); 

        user.setUsername(userUpdated.getUsername());
        user.setPassword(userUpdated.getPassword());
        user.setEmail(userUpdated.getEmail());
        user.setRole(userUpdated.getRole());
        user.setBalance(userUpdated.getBalance());
        user.setWishlist(userUpdated.getWishlist());
        user.setCart(userUpdated.getCart());
        user.setPurchases(userUpdated.getPurchases());
        return userRepository.save(user); 
    }



    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public List<Wishlist> getWishList(Integer userId){
        User user = getUserById(userId); 
        return wishlistRepository.; 
    }


    
    public String addProductToWishList(Integer userId, Integer productId){
        User user = userRepository.findById(userId).orElse(null); 
        if(user == null){
            return "User not found";
        }

        Product product = productRepository.findById(productId).orElse(null); 
        if(product == null){
            return "Product not found"; 
        }

        user.getWishlist().add(product);
        userRepository.save(user); 
        return "Product added to wishlist successfully"; 
    }



    public String removeProductFromWishList(Integer userId, Integer productId){
        User user = userRepository.findById(userId).orElse(null); 
        if(user == null){
            return "User not found"; 
        }

        Product product = productRepository.findById(productId).orElse(null); 
        if(product == null){
            return "Product not found"; 
        }

        boolean removed = user.getWishlist().remove(product);
        userRepository.save(user); 
        if(removed){
            return "Product removed from wishList successfully"; 
        }else {
            return "Product was not found in wishList"; 
        }


    }


    public List<Product> getCart(Integer userId){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Cart is empty"));

        return user.getCart(); 
    }

    
    public String addProductToCart(Integer userId, Integer productId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return "User not found"; 
        }

        Product product = productRepository.findById(productId).orElse(null); 
        if(product == null){
            return "Product not found"; 
        }

        user.getCart().add(product);
        userRepository.save(user); 
        return "Product added to cart successfully"; 
    }

    public String removeFromCart(Integer userId, Integer productId){
        User user = userRepository.findById(userId).orElse(null); 
        if(user == null){
            return "User not found"; 
        }


        Product product = productRepository.findById(productId).orElse(null); 
        if(product == null){
            return "Product not found"; 
        }

        user.getCart().remove(product);
        userRepository.save(user); 
        return "Product removed from cart successfully"; 
    }
    

    public String checkout(Integer userId, String discountCode){
        User user = userRepository.findById(userId).orElse(null); 
        if(user == null){
            return "User not found"; 
        }

        double totalCost = 0.0; 
        for(Product product : user.getCart()){
            Integer merchantId = getMerchantIdByProduct(product.getId()); 
            
            if(merchantId == null){
                return "Merchant not found for product " + product.getId(); 
            }

            MerchantStock stock = merchantStockService.getMerchantStockByProductAndMerchant(product.getId(), merchantId);  
            if(stock == null || stock.getStock() <= 0){
                return "Product " + product.getId() + " is out of stock"; 
            }

            totalCost += product.getPrice(); 
        }

        if(user.getBalance() < totalCost){
            return  "Insufficient balance"; 
        }

        if (discountCode != null && discountCode.equalsIgnoreCase("get15")) {
            totalCost = applyDiscount(totalCost, 15.0);
        }

        user.setBalance(user.getBalance() - totalCost);

        for(Product product : user.getCart()){
            Integer merchantId = getMerchantIdByProduct(product.getId()); 
            
            if(merchantId == null){
                return "Merchant not found for product " + product.getId(); 
            }

            MerchantStock stock = merchantStockService.getMerchantStockByProductAndMerchant(product.getId(), merchantId); 
            if(stock != null){
                stock.setStock(stock.getStock() - 1);
                merchantStockService.updateMerchantStock(stock.getId(), stock); 
            }
        }

        user.getPurchases().addAll(user.getCart()); 
        user.getCart().clear();
        userRepository.save(user); 

        return "Checkout successful"; 
    }


    public Integer getMerchantIdByProduct(Integer productId){
        for(MerchantStock stock : merchantStockService.getMerchantStocks()){
            if(stock.getProduct().getId().equals(productId)){
                return stock.getMerchant().getId(); 
            }
        }
        return null; 
    }

}
