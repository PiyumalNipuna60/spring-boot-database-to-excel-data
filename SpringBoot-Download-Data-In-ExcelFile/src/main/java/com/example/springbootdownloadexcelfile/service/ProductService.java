package com.example.springbootdownloadexcelfile.service;



import com.example.springbootdownloadexcelfile.entity.Product;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


public interface ProductService {

    List<Product> getAllProducts();

    Product insertProductIntoDatabase(Product product);

    ByteArrayInputStream getDataDownloaded() throws IOException;
}
