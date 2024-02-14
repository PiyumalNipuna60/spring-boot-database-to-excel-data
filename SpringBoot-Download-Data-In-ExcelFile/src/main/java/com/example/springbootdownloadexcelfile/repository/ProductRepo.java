package com.example.springbootdownloadexcelfile.repository;


import com.example.springbootdownloadexcelfile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
