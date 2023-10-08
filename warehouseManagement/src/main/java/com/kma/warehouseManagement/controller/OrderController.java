package com.kma.warehouseManagement.controller;

import com.kma.warehouseManagement.entityDto.OrderDto;
import com.kma.warehouseManagement.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    public ResponseEntity<String> addOrder(HttpServletRequest request, @RequestBody OrderDto orderDto) {
        orderService.addOrder(request,orderDto);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/getlist")
    public ResponseEntity<List<OrderDto>> getList(HttpServletRequest request) {
        return ResponseEntity.ok(orderService.getList(request));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("success");
    }


}
