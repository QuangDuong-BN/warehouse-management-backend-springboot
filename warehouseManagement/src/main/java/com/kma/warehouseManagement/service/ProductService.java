package com.kma.warehouseManagement.service;

import com.kma.warehouseManagement.entity.Product;
import com.kma.warehouseManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteProductById(id);
    }
    public List<Product> getlist(){
        return productRepository.findAll();
    }

    public Product getProductById(Integer id){
        return productRepository.findById(id).get();
    }
}
