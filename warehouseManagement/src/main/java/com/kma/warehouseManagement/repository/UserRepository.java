package com.kma.warehouseManagement.repository;

import com.kma.warehouseManagement.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // tim kiem user theo email
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);


    @Query("SELECT COUNT(u) FROM User u WHERE u.email = :email")
    Long countByEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.username = :username")
    Long countByUsername(@Param("username") String username);


    // admin repository
    @Transactional
    @Modifying
    //deleteMarketById
    void deleteUserById (Integer id);

    @Query("SELECT u FROM User u")
    Iterable<User> findAllUser ();

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.username = :username WHERE u.id = :id")
    void updateById( @Param("id") Integer id, @Param("name") String name, @Param("email") String email, @Param("username") String username);
}
