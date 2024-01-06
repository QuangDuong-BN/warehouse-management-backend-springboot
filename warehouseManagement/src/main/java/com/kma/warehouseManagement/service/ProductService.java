package com.kma.warehouseManagement.service;

import com.kma.warehouseManagement.entity.Product;
import com.kma.warehouseManagement.enumCustom.TypeProduct;
import com.kma.warehouseManagement.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public List<Product> getlist(TypeProduct type){
        return productRepository.findAllByType(type);
    }

    public Product getProductById(Integer id){
        return productRepository.findById(id).get();
    }

//    public Object getReport(HttpServletRequest request) {
//        return productRepository.getReport(request);
//    }

    public Object getExpiration(HttpServletRequest request) {
        LocalDate currentDate = LocalDate.now();
        return productRepository.getExpiration(currentDate);
    }

    public Object getInventoryQuantity(HttpServletRequest request) {
        return productRepository.count();
    }

    public Object getStillExpired(HttpServletRequest request, Integer id) {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDate1 = LocalDate.now().plusDays(id);
        return productRepository.getStillExpired(currentDate,currentDate1);
    }
}
