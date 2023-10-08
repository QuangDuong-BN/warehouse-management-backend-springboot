package com.kma.warehouseManagement.repository;

import com.kma.warehouseManagement.entity.Market;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Market m SET m.name = :name, m.address = :address WHERE m.id = :id")
    void updateMarketById(@Param("id") Integer id, @Param("name") String name, @Param("address") String address);

    @Transactional
    @Modifying
    void deleteMarketById(Integer id);
}
