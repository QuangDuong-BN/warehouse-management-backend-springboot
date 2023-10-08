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

//    public void updateProduct(Integer id, Product product) {
//        productRepository.updateProductById(id, product.getName(), product.getMaSp(), product.getSoLuong(), product.getDate(), product.getCategory());
//    }

    public void deleteProduct(Integer id) {
        productRepository.deleteProductById(id);
    }
    public List<Product> getlist(){
        return productRepository.findAll();
    }
}
