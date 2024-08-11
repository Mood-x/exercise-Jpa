package com.example.capstone_1.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 3, message = "Username have to be more than 3 length long")
    private String username;

    @NotEmpty(message = "Password must be not empty!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,20}$", message = "Password size should be between 6 to 20")
    private String password;


    @NotEmpty(message = "Email must not be empty")
    @Email
    private String email; 

    @NotEmpty(message = "Role must be not empty")
    @Pattern(regexp = "^(Admin|Customer|admin|customer)$", message = "you have to be ('Admin', 'Customer')")
    private String role; 


    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance have to be positive")
    private double balance;

    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlist = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Cart> cart = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Purchases> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>(); 
}
