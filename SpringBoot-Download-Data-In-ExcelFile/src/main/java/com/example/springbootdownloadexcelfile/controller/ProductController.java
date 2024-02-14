package com.example.springbootdownloadexcelfile.controller;

import com.example.springbootdownloadexcelfile.entity.Product;
import com.example.springbootdownloadexcelfile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("/insert")
    public Product insertProductInDb(@RequestBody Product product) {
        return  productService.insertProductIntoDatabase(product);
    }

    @GetMapping
    public APIResponse<List<Product>> getProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return new APIResponse<>(allProducts.size(), allProducts);
    }


    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> download() throws IOException {
          String fileName ="products.xlsx";
         ByteArrayInputStream inputStream = productService.getDataDownloaded();
         InputStreamResource    response = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                 .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+fileName)
                 .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(response);
    }

}
