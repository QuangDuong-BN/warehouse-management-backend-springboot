package com.kma.warehouseManagement.controller;

import com.kma.warehouseManagement.entity.Market;
import com.kma.warehouseManagement.entityDto.MarketDto;
import com.kma.warehouseManagement.repository.MarketRepository;
import com.kma.warehouseManagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addMarket(@RequestBody Market market) {
        marketRepository.save(market);
        return ResponseEntity.ok("success");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMarket(HttpServletRequest request,@RequestBody MarketDto marketDto) {
        marketRepository.updateMarketById(userService.getMarketIdByToken(request), marketDto.getName(), marketDto.getAddress());
        System.out.println(marketDto.getName());
        System.out.println(marketDto.getAddress());
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMarket(@PathVariable("id") Integer id) {
        marketRepository.deleteMarketById(id);
        return ResponseEntity.ok("success");
    }

}

