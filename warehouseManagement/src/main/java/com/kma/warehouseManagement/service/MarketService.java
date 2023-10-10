package com.kma.warehouseManagement.service;

import com.kma.warehouseManagement.entity.Market;
import com.kma.warehouseManagement.entity.User;
import com.kma.warehouseManagement.enumCustom.RoleUser;
import com.kma.warehouseManagement.exception.AccessDeniedException;
import com.kma.warehouseManagement.repository.MarketRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private UserService userService;
    public void addMarket(Market market) {
        marketRepository.save(market);
    }

    public void updateMarket(Integer id, Market market) {
        marketRepository.updateMarketById(id, market.getName(), market.getAddress());
    }

    public void deleteMarket(Integer id) {
        marketRepository.deleteMarketById(id);
    }

    public List<Market> getlist(HttpServletRequest request) {
        User user = userService.getUserByToken(request);
        if (user.getRole() == RoleUser.ADMIN) {
            return marketRepository.findAll();
        }
        else
            throw new AccessDeniedException("Access Denied");
    }


}
