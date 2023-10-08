package com.kma.warehouseManagement.repository;

import com.kma.warehouseManagement.entity.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.ArrayList;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    ArrayList<Item> findAllByOrderId(Integer orderId);

    List<Item> findByOrderId(Integer orderId);
    @Transactional
    @Modifying
    void deleteByOrderId(Integer lineItems);
}
