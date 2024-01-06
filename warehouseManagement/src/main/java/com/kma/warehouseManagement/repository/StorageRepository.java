package com.kma.warehouseManagement.repository;

import com.kma.warehouseManagement.entity.Storage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Storage s SET s.name = :newName,s.marketId = :newMarketId, s.address = :newAddress, s.latitude= :newlatitude, s.longtitude= :newlongtitude WHERE s.id = :id")
    void updateStorageById(@Param("id") Integer id, @Param("newName") String newName, @Param("newMarketId") Integer newMarketId, @Param("newAddress") String newAddress,
                           @Param("newlatitude") String newlatitude, @Param("newlongtitude") String newlongtitude);

    @Transactional
    @Modifying
    void deleteStorageById(Integer id);

    @Query("SELECT s FROM Storage s WHERE s.marketId = :marketId")
    ArrayList<Storage> findAllByMarketId(Integer marketId);
    // su dung trong check don hang
}
