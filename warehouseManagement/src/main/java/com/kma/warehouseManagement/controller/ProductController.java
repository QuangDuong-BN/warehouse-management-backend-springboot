package com.kma.warehouseManagement.controller;

import com.kma.warehouseManagement.entity.Product;
import com.kma.warehouseManagement.entity.Storage;
import com.kma.warehouseManagement.service.ProductService;
import com.kma.warehouseManagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok("success");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/getlist")
    public ResponseEntity<?> getList(HttpServletRequest request) throws AccessException {
        return ResponseEntity.ok(productService.getlist());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getList(HttpServletRequest request,@PathVariable Integer id) throws AccessException {
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
