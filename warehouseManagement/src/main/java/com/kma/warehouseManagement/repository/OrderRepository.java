package com.kma.warehouseManagement.repository;


import com.kma.warehouseManagement.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o")
    ArrayList<Order> findAllOrders();
    @Transactional
    @Modifying
    @Query("SELECT o FROM Order o")
    ArrayList<Order> fin();
}
