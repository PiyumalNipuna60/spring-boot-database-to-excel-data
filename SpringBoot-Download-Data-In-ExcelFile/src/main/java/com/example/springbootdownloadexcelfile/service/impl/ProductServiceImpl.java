package com.example.springbootdownloadexcelfile.service.impl;


import com.example.springbootdownloadexcelfile.entity.Product;
import com.example.springbootdownloadexcelfile.repository.ProductRepo;
import com.example.springbootdownloadexcelfile.service.ProductService;

import com.example.springbootdownloadexcelfile.util.ExcelUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements ProductService {
   @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product insertProductIntoDatabase(Product product) {
        return productRepo.save(product);
    }

    @Override
    public ByteArrayInputStream getDataDownloaded() throws IOException {
        List<Product> products = productRepo.findAll();
        return ExcelUtil.dataToExcel(products);

    }

    @PostConstruct
    public void initDB() {
        List<Product> products = IntStream.rangeClosed(1, 300)
                .mapToObj(i -> new Product(i,"product" + i, 1000.0, new Random().nextInt(50000)))
               .collect(Collectors.toList());
        productRepo.saveAll(products);
   }
}
