package com.example.capstone_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_1.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
