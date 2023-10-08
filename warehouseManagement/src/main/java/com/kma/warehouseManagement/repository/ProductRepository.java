package com.kma.warehouseManagement.repository;

import com.kma.warehouseManagement.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    @Transactional
//    @Modifying
//    @Query("UPDATE Product p SET p.name = :newName, p.maSp = :newMaSp, p.soLuong = :newSoLuong, p.date = :newDate, p.category = :newCategory WHERE p.id = :id")
//            void updateProductById(@Param("id") Integer id, @Param("newName")String newName, @Param("newMaSp")String newMaSp, @Param("newSoLuong")Integer newSoLuong,
//                                   @Param("newDate") LocalDate newDate, @Param("newCategory")String newCategory);

    @Transactional
    @Modifying
    void deleteProductById(Integer id);

    Optional<Product> findByIdAndStorageId(Integer id,Integer storageId);
}
