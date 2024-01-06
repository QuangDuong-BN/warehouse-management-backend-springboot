package com.kma.warehouseManagement.repository;

import com.kma.warehouseManagement.entity.Product;
import com.kma.warehouseManagement.enumCustom.TypeProduct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
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

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.soLuong = :soLuongConLai WHERE p.id = :id")
    void updateProductById(Integer id, Integer soLuongConLai);

    @Query("SELECT p FROM Product p WHERE p.type = :type")
    List<Product> findAllByType(TypeProduct type);


    @Query("SELECT p FROM Product p WHERE p.date < :currentDate")
    List<Product> getExpiration(LocalDate currentDate);

    @Query("SELECT p FROM Product p WHERE p.date >= :currentDate AND p.date < :currentDate1")
    List<Product> getStillExpired(LocalDate currentDate,LocalDate currentDate1);
}
